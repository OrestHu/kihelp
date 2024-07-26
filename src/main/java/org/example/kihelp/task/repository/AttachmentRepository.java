package org.example.kihelp.task.repository;

import org.example.kihelp.task.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
