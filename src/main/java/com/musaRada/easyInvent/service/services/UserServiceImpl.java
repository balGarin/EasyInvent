package com.musaRada.easyInvent.service.services;

import com.musaRada.easyInvent.dto.in.UserDtoSingUp;
import com.musaRada.easyInvent.dto.in.UserEmailDtoIn;
import com.musaRada.easyInvent.dto.in.UserUpdateDto;
import com.musaRada.easyInvent.dto.out.UserDtoOut;
import com.musaRada.easyInvent.dto.out.UserEmailDtoOut;
import com.musaRada.easyInvent.exception.IncorrectDataException;
import com.musaRada.easyInvent.exception.NotFoundException;
import com.musaRada.easyInvent.mapper.EmailMapper;
import com.musaRada.easyInvent.mapper.UserMapper;
import com.musaRada.easyInvent.model.User;
import com.musaRada.easyInvent.model.UserEmail;
import com.musaRada.easyInvent.repository.UserEmailRepository;
import com.musaRada.easyInvent.repository.UserRepository;
import com.musaRada.easyInvent.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserEmailRepository userEmailRepository;
    private final UserRepository userRepository;
    private final EmailMapper emailMapper;
    private final UserMapper userMapper;

    @Override
    public String addEmailForSingUp(UserEmailDtoIn userEmailDtoIn) {
        UserEmail email = emailMapper.toUserEMail(userEmailDtoIn);
        try {
            email = userEmailRepository.save(email);

        } catch (RuntimeException exception) {
            throw new IncorrectDataException("Такой email уже существует");
        }

        return "Email успешно добавлен";


    }


    @Override
    public UserEmailDtoOut getEmailById(Long userEmailId) {
        UserEmail userEmail = userEmailRepository.findById(userEmailId)
                .orElseThrow(() -> new NotFoundException("Email c таким " + userEmailId + " не найден"));
        return emailMapper.toDto(userEmail);
    }

    @Override
    public UserEmailDtoOut updateUserEmail(UserEmailDtoIn email, Long emailId) {
        return null;
    }

    @Override
    public List<UserDtoOut> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    @Override
    public String removeUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с +" + userId + " не найден!"));
        userRepository.delete(user);
        return "Пользователь " + user.getUsername() + " успешно удален!";
    }

//    @Override
//    public String updateUser(UserUpdateDto userUpdateDto) {
//
//    }

    @Override
    public UserDtoOut getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с +" + userId + " не найден!"));
        return userMapper.toDto(user);
    }
}
