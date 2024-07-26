package org.example.kihelp.user.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.mapper.UserRegisterRequestToUserMapper;
import org.example.kihelp.user.model.request.UserRegisterRequest;
import org.example.kihelp.user.model.resp.JwtUserResponse;
import org.example.kihelp.user.service.impl.UserServiceImpl;
import org.example.kihelp.user.usecase.UserCreateUseCase;
import org.example.kihelp.user.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import static org.example.kihelp.user.util.MessageError.BAD_CREDENTIALS;

@Component
@RequiredArgsConstructor
public class UserCreateUseCaseImpl implements UserCreateUseCase {
    private final UserServiceImpl userService;
    private final UserRegisterRequestToUserMapper userRegisterRequestToUserMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtUserResponse createUser(UserRegisterRequest registerRequest) {
        var user = userRegisterRequestToUserMapper.map(registerRequest);

        userService.createUser(user);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    registerRequest.telegramId(),
                    registerRequest.telegramId()
            ));
        }catch (BadCredentialsException e) {
            throw new BadCredentialsException(BAD_CREDENTIALS);
        }

        var userDetails = userService.loadUserByUsername(registerRequest.telegramId());
        var token = jwtUtils.generateToken(userDetails);

        return new JwtUserResponse(token);
    }
}
