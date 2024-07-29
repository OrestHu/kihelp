package org.example.kihelp.task.model.req;


import jakarta.validation.constraints.Size;

public record TaskUpdateRequest(
        @Size(min = 5, message = "Довжина заголовка повинна бути не менше ніж {min} символів")
        String title,
        String path,
        Integer price,
        Double discount
) {
}
