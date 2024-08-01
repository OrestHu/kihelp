package org.example.kihelp.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.example.kihelp.task.exception.TaskNotFoundException;
import org.example.kihelp.task.model.Task;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.req.TaskUpdateRequest;
import org.example.kihelp.task.repository.TaskRepository;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.teacher.exception.TeacherAlreadyExist;
import org.example.kihelp.teacher.model.Teacher;
import org.example.kihelp.user.api.service.UserApiService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.example.kihelp.task.util.MessageError.*;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private String FILE_DIRECTORY = "tasks/answer/";
    private final TaskRepository taskRepository;
    private final UserApiService userApiService;

    @Override
    public void createTask(Task task) {
        var exist = taskRepository.existsByTitleAndTeacher(task.getTitle(), task.getTeacher());

        if(exist){
            throw new TeacherAlreadyExist(String.format(TASK_ALREADY_EXIST, task.getTitle(), task.getTeacher().getName()));
        }

        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Integer taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException(TASK_NOT_FOUND)
        );
    }

    @Override
    public List<Task> getTasksByTeacher(Teacher teacher) {
        return taskRepository.findByTeacher(teacher);
    }

    @Override
    public String programTask(Integer taskId, TaskProgramRequest request) {
        var userDetails = userApiService.currentUserAccount();
        var path = getTaskById(taskId).getPath();

        try {
            ProcessBuilder pb = new ProcessBuilder();
            String pythonCommand = System.getProperty("python.command", "python3");
            pb.command(pythonCommand, path);
            pb.command().addAll(request.args());
            pb.command().add(userDetails.telegramId());

            Process process = pb.start();

            // Read standard output
            BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // Read standard error
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            boolean completed = process.waitFor(60, TimeUnit.SECONDS);
            if (!completed) {
                process.destroyForcibly();
                throw new RuntimeException("PROCESS_TIME_OUT");
            }

            String line;
            System.out.println("Standard Output:");
            while ((line = stdOutput.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("Standard Error:");
            while ((line = stdError.readLine()) != null) {
                System.err.println(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("here");

        var extension = findFileAndDetermineExtension(FILE_DIRECTORY, String.format("task_%s", userDetails.telegramId()));

        return String.format("task_%s.%s", userDetails.telegramId(), extension);
    }

    @Override
    public void deleteTask(Integer taskId) {
        var task = getTaskById(taskId);

        taskRepository.delete(task);
    }

    @Override
    public void updateTask(Integer taskId, TaskUpdateRequest request) {
        var task = getTaskById(taskId);

        if(request.title() != null && !request.title().isEmpty()){
            task.setTitle(request.title());
        }
        if(request.path() != null && !request.path().isEmpty()){
            task.setPath(request.path());
        }
        if(request.price() != null){
            task.setPrice(request.price());
        }
        if(request.discount() != null){
            task.setDiscount(request.discount());
        }

        taskRepository.save(task);
    }

    private static String findFileAndDetermineExtension(String directoryPath, String fileNameWithoutExtension) {
        try (Stream<Path> walk = Files.walk(Paths.get(directoryPath))) {
            return walk
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(f -> f.startsWith(fileNameWithoutExtension + "."))
                    .findFirst()
                    .map(FilenameUtils::getExtension)
                    .orElseThrow(() -> new RuntimeException(ERROR_DURING_PROGRAM_TASK));
        } catch (IOException e) {
            throw new RuntimeException(DIRECTORY_NOT_FOUND, e);
        }
    }
}
