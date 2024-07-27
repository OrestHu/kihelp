package org.example.kihelp.teacher.model.request;

import jakarta.validation.constraints.NotBlank;

public record TeacherRequest(
        @NotBlank(message = "Ініціали викладача не повинні бути пустими")
        String name,
        Integer subjectId
) {
}
