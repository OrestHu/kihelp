package org.example.kihelp.subject.usecase;

import jakarta.validation.Valid;
import org.example.kihelp.subject.model.request.SubjectRequest;
import org.springframework.validation.annotation.Validated;

@Validated
public interface SubjectCreateUseCase {
    void createSubject(@Valid SubjectRequest subject);
}
