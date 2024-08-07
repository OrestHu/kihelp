package org.example.kihelp.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.exception.CustomMicroServiceException;
import org.example.kihelp.task.exception.TaskNotFoundException;
import org.example.kihelp.task.model.Task;
import org.example.kihelp.task.model.resp.ProgramResponse;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.req.TaskUpdateRequest;
import org.example.kihelp.task.model.req.ProgramRequest;
import org.example.kihelp.task.repository.TaskRepository;
import org.example.kihelp.task.service.HistoryService;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.history.HistoryDeleteUseCase;
import org.example.kihelp.teacher.exception.TeacherAlreadyExist;
import org.example.kihelp.teacher.model.Teacher;
import org.example.kihelp.user.api.model.UserResponseApi;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.example.kihelp.task.util.MessageError.*;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final RestTemplate restTemplate;
    private final TaskRepository taskRepository;
    private final HistoryDeleteUseCase historyDeleteUseCase;

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
        return taskRepository.findByTeacher(teacher, Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public ProgramResponse programTask(Integer taskId, TaskProgramRequest request, UserResponseApi response) {
        var task = getTaskById(taskId);
        var titleOfSubject = task.getTeacher().getSubject().getTitle();

        ProgramRequest programRequest = new ProgramRequest(
                response.telegramId(),
                task.getPath(),
                task.getTitle(),
                titleOfSubject,
                request.args()
        );

        ProgramResponse programResponse;

        try {
            programResponse = restTemplate.postForObject("http://13.48.203.111:8000/api/v1/programs/program", programRequest, ProgramResponse.class);
        } catch (HttpServerErrorException e) {
            throw new CustomMicroServiceException(e.getMessage());
        }

        assert programResponse != null;

        return programResponse;
    }

    @Override
    public void deleteTask(Integer taskId) {
        var task = getTaskById(taskId);

        historyDeleteUseCase.deleteHistoriesByTask(taskId);
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
}
