package org.example.kihelp.task.model.req;

import java.util.List;

public record ProgramRequest(
        String telegramId,
        String path,
        String taskTitle,
        String subjectTitle,
        List<String> args
) {
}
