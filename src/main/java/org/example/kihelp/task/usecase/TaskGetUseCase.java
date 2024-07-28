package org.example.kihelp.task.usecase;

import org.example.kihelp.task.model.resp.TaskInfoResponse;
import org.example.kihelp.task.model.resp.TaskResponse;

import java.util.List;

public interface TaskGetUseCase {
    TaskInfoResponse getTaskInfo(Integer taskId);
    List<TaskResponse> getTasksBySubjectAndTeacher(Integer subjectId, Integer teacherId);
}
