package org.example.kihelp.task.controller;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.req.ArgumentRequest;
import org.example.kihelp.task.model.resp.ArgumentResponse;
import org.example.kihelp.task.usecase.argument.ArgumentCreateUseCase;
import org.example.kihelp.task.usecase.argument.ArgumentGetUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/arguments")
@RequiredArgsConstructor
public class ArgumentController {
    private final ArgumentCreateUseCase argumentCreateUseCase;
    private final ArgumentGetUseCase argumentGetUseCase;

    @PostMapping("/argument")
    public void createArgument(@RequestBody ArgumentRequest argumentRequest){
        argumentCreateUseCase.createArgument(argumentRequest);
    }

    @GetMapping("/argument")
    public List<ArgumentResponse> getArguments(){
        return argumentGetUseCase.getArguments();
    }

    @GetMapping("/argument/{argument_id}")
    public ArgumentResponse getArgument(@PathVariable("argument_id") Integer argumentId){
        return argumentGetUseCase.getArgument(argumentId);
    }
}
