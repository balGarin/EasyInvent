package com.musaRada.easyInvent.mapper;

import com.musaRada.easyInvent.dto.in.UserEmailDtoIn;
import com.musaRada.easyInvent.dto.out.UserEmailDtoOut;
import com.musaRada.easyInvent.model.UserEmail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmailMapper {
    UserEmail toUserEMail(UserEmailDtoIn userEmailDtoIn);
    UserEmailDtoOut toDto(UserEmail userEmail);
}
