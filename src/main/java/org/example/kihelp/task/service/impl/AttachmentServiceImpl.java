package org.example.kihelp.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.Attachment;
import org.example.kihelp.task.repository.AttachmentRepository;
import org.example.kihelp.task.service.AttachmentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Override
    public void storeFile(Attachment attachment){

        attachmentRepository.save(attachment);
    }
}
