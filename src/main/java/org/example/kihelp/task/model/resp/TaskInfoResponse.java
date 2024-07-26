package org.example.kihelp.task.model.resp;

import java.util.List;

public record TaskInfoResponse(
        String title,
        List<String> args
) {
}
