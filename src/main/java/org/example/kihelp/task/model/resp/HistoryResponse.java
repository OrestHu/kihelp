package org.example.kihelp.task.model.resp;

public record HistoryResponse(
        Long id,
        String name,
        String createdTimeStamp,
        Long userId,
        Integer taskId
) {
}
