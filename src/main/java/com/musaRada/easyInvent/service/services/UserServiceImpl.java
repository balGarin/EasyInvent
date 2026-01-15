package com.musaRada.easyInvent.service.services;

import com.musaRada.easyInvent.dto.in.UserDtoSingUp;
import com.musaRada.easyInvent.dto.in.UserUpdateDto;
import com.musaRada.easyInvent.exception.IncorrectDataException;
import com.musaRada.easyInvent.exception.NotFoundException;
import com.musaRada.easyInvent.model.User;
import com.musaRada.easyInvent.model.UserEmail;
import com.musaRada.easyInvent.repository.UserEmailRepository;
import com.musaRada.easyInvent.repository.UserRepository;
import com.musaRada.easyInvent.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserEmailRepository userEmailRepository;
    private  final UserRepository userRepository;

    @Override
    public String addEmailForSingUp(UserEmail email) {

       try {
           email = userEmailRepository.save(email);

       }catch (RuntimeException exception){
           throw  new IncorrectDataException("Такой email уже существует");
       }

       return "Email успешно добавлен";


    }


    @Override
    public UserEmail getEmailById(Long userEmailId) {
        return userEmailRepository.findById(userEmailId)
                .orElseThrow(()->new NotFoundException("Email c таким "+userEmailId+" не найден"));
    }

    @Override
    public UserEmail updateUserEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String removeUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("Пользователь с +"+userId+" не найден!"));
        userRepository.delete(user);
        return "Пользователь "+user.getUsername()+" успешно удален!";
    }

    @Override
    public String updateUser(UserUpdateDto userUpdateDto) {
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("Пользователь с +"+userId+" не найден!"));
    }
}
