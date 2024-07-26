package org.example.kihelp.task.model.resp;


public record TaskProgramResponse(
        String fileName,
        String fileType,
        byte[] fileData
) {
}
