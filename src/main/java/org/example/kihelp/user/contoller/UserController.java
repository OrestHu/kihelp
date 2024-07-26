package org.example.kihelp.user.contoller;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.model.request.UserRegisterRequest;
import org.example.kihelp.user.model.resp.JwtUserResponse;
import org.example.kihelp.user.model.resp.UserResponse;
import org.example.kihelp.user.usecase.UserCreateUseCase;
import org.example.kihelp.user.usecase.UserGetUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserCreateUseCase userCreateUseCase;
    private final UserGetUseCase userGetUseCase;

    @PostMapping("/user")
    public JwtUserResponse loginUser(@RequestBody UserRegisterRequest registerRequest){
        return userCreateUseCase.createUser(registerRequest);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        return userGetUseCase.getUsers();
    }

    @GetMapping("/user/{user_id}")
    public UserResponse getUser(@PathVariable("user_id") Long userId){
        return userGetUseCase.getUser(userId);
    }
}
