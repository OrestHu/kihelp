package org.example.kihelp.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.Task;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.repository.TaskRepository;
import org.example.kihelp.task.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.kihelp.task.util.MessageError.TASK_ALREADY_EXIST;
import static org.example.kihelp.task.util.MessageError.TASK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    @Value("${answer}")
    private String FILE_DIRECTORY;
    private final TaskRepository taskRepository;

    @Override
    public void createTask(Task task) {
        var exist = taskRepository.existsByTitle(task.getTitle());

        if (exist) {
            throw new RuntimeException(String.format(TASK_ALREADY_EXIST, task.getTitle()));
        }

        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Integer taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException(TASK_NOT_FOUND)
        );
    }

    @Override
    public String programTask(Integer taskId, TaskProgramRequest request) {
        var task = getTaskById(taskId);
        var path = task.getPath();

        System.out.println(path);

        try {
            List<String> command = new ArrayList<>();
            command.add("python");
            command.add(path);
            command.addAll(request.args());
            command.add(request.userId().toString());

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        var extension = findFileAndDetermineExtension(FILE_DIRECTORY, String.format("task_%s", request.userId()));

        return String.format("task_%s.%s", request.userId(), extension);
    }

    private static String findFileAndDetermineExtension(String directoryPath, String fileNameWithoutExtension) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            return "";
        }

        File[] files = directory.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile() && file.getName().startsWith(fileNameWithoutExtension + ".")) {
                    return getFileExtension(file.getName());
                }
            }
        }
        return "";
    }

    private static String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
