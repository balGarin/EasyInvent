package com.musaRada.easyInvent.service.interfaces;

import com.musaRada.easyInvent.dto.in.UserEmailDtoIn;
import com.musaRada.easyInvent.dto.in.UserUpdateDto;
import com.musaRada.easyInvent.dto.out.UserDtoOut;
import com.musaRada.easyInvent.dto.out.UserEmailDtoOut;
import com.musaRada.easyInvent.model.User;
import com.musaRada.easyInvent.model.UserEmail;

import java.util.List;

public interface UserService {

    String addEmailForSingUp(UserEmailDtoIn email);


    UserEmailDtoOut getEmailById(Long userEmailId);


    UserEmailDtoOut updateUserEmail(UserEmailDtoIn email,Long emailId);

    List<UserDtoOut> getAllUsers();

    String removeUser(Long userId);

//    String updateUser(UserUpdateDto userUpdateDto);

    UserDtoOut getUserById(Long userId);





}
