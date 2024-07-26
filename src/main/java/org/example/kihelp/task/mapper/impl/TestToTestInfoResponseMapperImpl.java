package org.example.kihelp.task.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.TestToTestInfoResponseMapper;
import org.example.kihelp.task.model.Task;
import org.example.kihelp.task.model.resp.TestInfoResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class TestToTestInfoResponseMapperImpl implements TestToTestInfoResponseMapper {
    @Override
    public TestInfoResponse map(Task task, Integer repeat) {
        var argument = task.getArguments();

        var args = new ArrayList<String>();
        for(int i = 0; i < repeat; i++) {
            args.add(String.format("%s %s",argument.get(0).getName(), i + 1));
        }

        return new TestInfoResponse(task.getTitle(), args);
    }
}
