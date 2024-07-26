package org.example.kihelp.user.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.exception.RoleNotFoundException;
import org.example.kihelp.user.mapper.UserRegisterRequestToUserMapper;
import org.example.kihelp.user.model.User;
import org.example.kihelp.user.model.request.UserRegisterRequest;
import org.example.kihelp.user.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.example.kihelp.user.util.MessageError.ROLE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class UserRegisterRequestToUserMapperImpl implements UserRegisterRequestToUserMapper {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setTelegramId(userRegisterRequest.telegramId());
        user.setUsername(userRegisterRequest.username());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.telegramId()));
        user.setLogo(userRegisterRequest.logo());

        var role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException(String.format(ROLE_NOT_FOUND, "ROLE_USER")));

        user.setRoles(List.of(role));

        return user;
    }
}
