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
import org.example.kihelp.user.api.model.UserResponseApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.kihelp.task.util.MessageError.*;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    @Value("${file.directory}")
    private String FILE_DIRECTORY;

    private final TaskRepository taskRepository;

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
    public String programTask(Integer taskId, TaskProgramRequest request, UserResponseApi response) {
        var path = getTaskById(taskId).getPath();

        try {
            List<String> command = new ArrayList<>(List.of(
                    System.getProperty("python.command", "python3"),
                    path
            ));
            command.addAll(request.args());
            command.add(response.telegramId());

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);

            Process process = pb.start();

            CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    return reader.lines().collect(Collectors.joining("\n"));
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });

            if (!process.waitFor(60, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                throw new RuntimeException(PROCESS_TIME_OUT);
            }

            String result = output.get();
            System.out.println("Вивід Python:\n" + result);

        } catch (Exception e) {
            throw new RuntimeException("Помилка виконання Python скрипту", e);
        }

        var extension = findFileAndDetermineExtension(FILE_DIRECTORY, String.format("task_%s", response.telegramId()));
        return String.format("task_%s.%s", response.telegramId(), extension);
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
        if(request.info() != null && !request.info().isEmpty()){
            task.setInfo(request.info());
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
        if(request.visible() != null){
            task.setVisible(request.visible());
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
