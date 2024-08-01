package org.example.kihelp.task.model.resp;

import java.util.List;

public record TaskResponse(
        Integer id,
        String subjectTitle,
        String title,
        Double price,
        Double discount,
        Boolean type,
        Integer teacherId,
        List<String> args
) {
}
