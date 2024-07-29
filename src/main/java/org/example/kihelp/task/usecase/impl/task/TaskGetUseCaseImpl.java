package org.example.kihelp.task.usecase.impl.task;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.TaskToTaskResponseMapper;
import org.example.kihelp.task.model.resp.TaskResponse;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.task.TaskGetUseCase;
import org.example.kihelp.teacher.service.TeacherService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskGetUseCaseImpl implements TaskGetUseCase {
    private final TaskService taskService;
    private final TeacherService teacherService;
    private final TaskToTaskResponseMapper taskToTaskResponseMapper;

    @Override
    public TaskResponse getTaskInfo(Integer taskId) {
        var task = taskService.getTaskById(taskId);

        return taskToTaskResponseMapper.map(task);
    }

    @Override
    public List<TaskResponse> getTasksByTeacher(Integer teacherId) {
        var teacher = teacherService.getTeacher(teacherId);
        var tasks = taskService.getTasksByTeacher(teacher);

        return tasks.stream().map(taskToTaskResponseMapper::map).toList();
    }
}
