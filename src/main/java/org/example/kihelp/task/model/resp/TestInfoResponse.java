package org.example.kihelp.task.model.resp;

import java.util.List;

public record TestInfoResponse(
        String title,
        List<String> args
) {
}
