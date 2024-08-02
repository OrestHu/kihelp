package org.example.kihelp.task.model.req;

import java.time.Instant;

public record HistoryRequest(
        String name,
        Instant createdTimeStamp,
        Long userId,
        Integer taskId
) {
}
