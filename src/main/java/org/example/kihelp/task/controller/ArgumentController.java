package org.example.kihelp.task.controller;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.req.ArgumentRequest;
import org.example.kihelp.task.model.resp.ArgumentResponse;
import org.example.kihelp.task.usecase.argument.ArgumentCreateUseCase;
import org.example.kihelp.task.usecase.argument.ArgumentDeleteUseCase;
import org.example.kihelp.task.usecase.argument.ArgumentGetUseCase;
import org.example.kihelp.task.usecase.argument.ArgumentUpdateUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/arguments")
@RequiredArgsConstructor
public class ArgumentController {
    private final ArgumentCreateUseCase argumentCreateUseCase;
    private final ArgumentGetUseCase argumentGetUseCase;
    private final ArgumentDeleteUseCase argumentDeleteUseCase;
    private final ArgumentUpdateUseCase argumentUpdateUseCase;

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

    @DeleteMapping("/argument/{argument_id}")
    public void deleteArgument(@PathVariable("argument_id") Integer argumentId){
        argumentDeleteUseCase.deleteArgument(argumentId);
    }

    @PutMapping("/argument/{argument_id}")
    public void updateArgument(@PathVariable("argument_id") Integer argumentId,
                               @RequestBody ArgumentRequest argumentRequest){
        argumentUpdateUseCase.updateArgument(argumentId, argumentRequest);
    }
}
