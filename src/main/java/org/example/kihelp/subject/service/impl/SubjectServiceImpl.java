package org.example.kihelp.subject.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.exception.SubjectAlreadyExist;
import org.example.kihelp.subject.exception.SubjectNotFoundException;
import org.example.kihelp.subject.model.Subject;
import org.example.kihelp.subject.repository.SubjectRepository;
import org.example.kihelp.subject.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.kihelp.subject.util.MessageError.SUBJECT_ALREADY_EXISTS;
import static org.example.kihelp.subject.util.MessageError.SUBJECT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public void createSubject(Subject subject) {
        var exist = subjectRepository.existsByTitle(subject.getTitle());

        if (exist) {
            throw new SubjectAlreadyExist(String.format(SUBJECT_ALREADY_EXISTS, subject.getTitle()));
        }

        subjectRepository.save(subject);
    }

    @Override
    public List<Subject> findSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findSubject(Integer subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException(SUBJECT_NOT_FOUND));
    }

    @Override
    public void deleteSubjectById(Integer subjectId) {
        var subject = findSubject(subjectId);

        subjectRepository.delete(subject);
    }

    @Override
    public void updateSubject(Integer subjectId, Subject updateSubject) {
        var subject = findSubject(subjectId);

        if(updateSubject.getTitle() != null && !updateSubject.getTitle().isEmpty()){
            subject.setTitle(updateSubject.getTitle());
        }

        subjectRepository.save(subject);
    }
}
