package org.example.kihelp.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.exception.UserNotFoundException;
import org.example.kihelp.user.model.User;
import org.example.kihelp.user.repository.UserRepository;
import org.example.kihelp.user.service.UserService;
import org.example.kihelp.wallet.model.Wallet;
import org.example.kihelp.wallet.service.WalletService;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.kihelp.user.util.MessageError.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final WalletService walletService;

    @Override
    public void createUser(User user) {
        var exist = userRepository.existsByTelegramId(user.getTelegramId());

        if(!exist){
            var userRes = userRepository.save(user);

            walletService.createWallet(userRes, new Wallet().builder().amount(0.0).build());
        } else {
            var updatedUser = userRepository.findByTelegramId(user.getTelegramId()).orElseThrow(
                    () -> new UserNotFoundException(USER_NOT_FOUND)
            );
            if(!user.getUsername().equals(updatedUser.getUsername())) {
                updatedUser.setUsername(user.getUsername());
                userRepository.save(updatedUser);
            }
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException(USER_NOT_FOUND)
                );
    }

    @Override
    public User getUserByTelegramId(String telegramId) {
        return userRepository.findByTelegramId(telegramId)
                .orElseThrow(
                        () -> new UserNotFoundException (USER_NOT_FOUND)
                );
    }

    @Override
    public UserDetails loadUserByUsername(String telegramId) throws UsernameNotFoundException {
        var user = getUserByTelegramId(telegramId);

        return new org.springframework.security.core.userdetails.User(
            user.getTelegramId(),
            user.getPassword(),
            user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }
}
