package org.example.kihelp.subject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.model.request.SubjectRequest;
import org.example.kihelp.subject.model.resp.SubjectResponse;
import org.example.kihelp.subject.usecase.SubjectCreateUseCase;
import org.example.kihelp.subject.usecase.SubjectDeleteUseCase;
import org.example.kihelp.subject.usecase.SubjectGetUseCase;
import org.example.kihelp.subject.usecase.SubjectUpdateUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectCreateUseCase subjectCreateUseCase;
    private final SubjectGetUseCase subjectGetUseCase;
    private final SubjectDeleteUseCase subjectDeleteUseCase;
    private final SubjectUpdateUseCase subjectUpdateUseCase;

    @PostMapping("/subject")
    public void createSubject(@Valid @RequestBody SubjectRequest subject) {
        subjectCreateUseCase.createSubject(subject);
    }

    @GetMapping("/subject")
    public List<SubjectResponse> findSubjects(){
        return subjectGetUseCase.findSubjects();
    }

    @GetMapping("/subject/{subject_id}")
    public SubjectResponse findSubject(@PathVariable("subject_id") Integer subjectId){
        return subjectGetUseCase.findSubject(subjectId);
    }

    @DeleteMapping("/subject/{subject_id}")
    public void deleteSubject(@PathVariable("subject_id") Integer subjectId){
        subjectDeleteUseCase.deleteSubject(subjectId);
    }

    @PutMapping("/subject/{subject_id}")
    public void updateSubject(@PathVariable("subject_id") Integer subjectId,
                              @Valid @RequestBody SubjectRequest subjectRequest){
        subjectUpdateUseCase.updateSubject(subjectId, subjectRequest);
    }
}
