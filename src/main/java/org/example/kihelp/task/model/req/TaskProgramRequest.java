package org.example.kihelp.task.model.req;

import java.util.List;

public record TaskProgramRequest(
        Long userId,
        Integer teacherId,
        String title,
        List<String> args
) {
}
