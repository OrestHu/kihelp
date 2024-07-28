package org.example.kihelp.task.model.resp;

public record TaskResponse(
        Integer id,
        String title,
        Integer price,
        Double discount,
        Integer subjectId,
        Integer teacherId
) {
}
