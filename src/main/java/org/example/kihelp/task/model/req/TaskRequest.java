package org.example.kihelp.task.model.req;

import java.util.List;

public record TaskRequest(
        String title,
        String path,
        Integer price,
        Integer subjectId,
        Integer teacherId,
        List<Integer> argumentsId
) {
}
