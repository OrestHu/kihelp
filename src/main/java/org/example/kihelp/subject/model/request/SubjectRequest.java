package org.example.kihelp.subject.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SubjectRequest(
        @NotBlank(message = "Заголовок для предмету не повинний бути пустим")
        @Size(min = 5, message = "Мінімальна довжина заголовка повинна бути не менше ніж 5 символів")
        String title
) {
}
