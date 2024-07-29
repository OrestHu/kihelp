package org.example.kihelp.task.model.resp;

import java.util.List;

public record TaskResponse(
        Integer id,
        String title,
        Integer price,
        Double discount,
        Boolean type,
        Integer subjectId,
        List<String> args
) {
}
