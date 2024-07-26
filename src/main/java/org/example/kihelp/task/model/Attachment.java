package org.example.kihelp.task.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "ki_help", name = "attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
}
