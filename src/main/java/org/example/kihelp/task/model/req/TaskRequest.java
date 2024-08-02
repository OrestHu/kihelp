package org.example.kihelp.task.model.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record TaskRequest(
        @NotBlank(message = "Заголовок для завдання не має бути пустим")
        @Size(min = 5, message = "Довжина заголовка повинна бути не менше ніж {min} символів")
        String title,
        String info,
        @NotBlank(message = "Шлях для файла для завдання не має бути пустим")
        String path,
        @NotNull(message = "Ціна не має дорівнювати null")
        Double price,
        Integer teacherId,
        Boolean type,
        List<Integer> argumentsId
) {
}
