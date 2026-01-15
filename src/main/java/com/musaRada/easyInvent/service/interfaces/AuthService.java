package com.musaRada.easyInvent.service.interfaces;

import com.musaRada.easyInvent.dto.in.UserDtoSingIn;
import com.musaRada.easyInvent.dto.in.UserDtoSingUp;
import com.musaRada.easyInvent.model.User;

import java.util.List;

public interface AuthService {

     User singUp(UserDtoSingUp userDtoSingUp);

    String singIn(UserDtoSingIn userDtoSingIn);






}
