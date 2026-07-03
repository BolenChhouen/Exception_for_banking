package com.bank.service;

import com.bank.dto.RegisterRequest;
import com.bank.entity.User;
import com.bank.repository.UserRepository;
import com.bank.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(
            RegisterRequest request
    ){
        User user = new User();

        user.setUsername(request.username());
        // must happen before save
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER.name());

        return userRepository.save(user);
    }
}
