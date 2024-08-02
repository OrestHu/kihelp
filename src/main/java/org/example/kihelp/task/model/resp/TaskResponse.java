package org.example.kihelp.task.model.resp;

import java.util.List;

public record TaskResponse(
        Integer id,
        String subjectTitle,
        String info,
        String title,
        Double price,
        Double discount,
        Boolean type,
        Boolean visible,
        Integer teacherId,
        List<String> args
) {
}
