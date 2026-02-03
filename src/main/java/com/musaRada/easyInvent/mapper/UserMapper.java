package com.musaRada.easyInvent.mapper;

import com.musaRada.easyInvent.dto.in.UserDtoSingUp;
import com.musaRada.easyInvent.dto.out.UserDtoOut;
import com.musaRada.easyInvent.model.User;
import com.musaRada.easyInvent.model.UserEmail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
@Mapping(target = "email",source = "userEmail")
    User toUser(UserDtoSingUp userDtoSingUp, UserEmail userEmail);
@Mapping(target = "email",expression = "java(user.getEmail().getEmail())")
    UserDtoOut toDto(User user);


List<UserDtoOut>toDto(List<User>users);
}
