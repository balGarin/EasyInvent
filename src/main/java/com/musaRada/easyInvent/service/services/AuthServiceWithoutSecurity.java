package com.musaRada.easyInvent.service.services;

import com.musaRada.easyInvent.dto.in.UserDtoSingIn;
import com.musaRada.easyInvent.dto.in.UserDtoSingUp;
import com.musaRada.easyInvent.dto.out.UserDtoOut;
import com.musaRada.easyInvent.exception.AuthException;
import com.musaRada.easyInvent.mapper.UserMapper;
import com.musaRada.easyInvent.model.User;
import com.musaRada.easyInvent.model.UserEmail;

import com.musaRada.easyInvent.repository.UserEmailRepository;
import com.musaRada.easyInvent.repository.UserRepository;
import com.musaRada.easyInvent.service.interfaces.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceWithoutSecurity implements AuthService {

    private final UserRepository userRepository;
    private final UserEmailRepository userEmailRepository;
    private final UserMapper userMapper;

    @Override
    public UserDtoOut singUp(UserDtoSingUp userDtoSingUp) {

        User user = new User();
        UserEmail userEmail = userEmailRepository.findByEmail(userDtoSingUp.getEmail())
                        .orElseThrow(()->new AuthException("У вас пока нет доступа"));
        user.setEmail(userEmail);
        user.setUsername(userDtoSingUp.getUsername());
        user.setPassword(userDtoSingUp.getPassword());
        user=userRepository.save(user);
        return  userMapper.toDto(user);

    }

    @Override
    public String singIn(UserDtoSingIn userDtoSingIn) {
        return null;
    }
}


