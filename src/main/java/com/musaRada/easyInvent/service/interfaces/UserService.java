package com.musaRada.easyInvent.service.interfaces;

import com.musaRada.easyInvent.dto.in.UserUpdateDto;
import com.musaRada.easyInvent.model.User;
import com.musaRada.easyInvent.model.UserEmail;

import java.util.List;

public interface UserService {

    String addEmailForSingUp(UserEmail email);


    UserEmail getEmailById(Long userEmailId);


    UserEmail updateUserEmail(String email);

    List<User> getAllUsers();

    String removeUser(Long userId);

    String updateUser(UserUpdateDto userUpdateDto);

    User getUserById(Long userId);





}
