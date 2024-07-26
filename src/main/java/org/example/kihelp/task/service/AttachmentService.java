package org.example.kihelp.task.service;

import org.example.kihelp.task.model.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AttachmentService {
    Attachment storeFile(MultipartFile file) throws IOException;
}
