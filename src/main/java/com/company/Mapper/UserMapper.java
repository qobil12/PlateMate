package com.company.Mapper;

import com.company.DTO.UserCreateDTO;
import com.company.DTO.UserResponseDTO;
import com.company.Entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BankDetailsMapper.class})
public abstract class UserMapper {

    public abstract UserResponseDTO toUserResponseDTO(final UserEntity user);

    public abstract UserEntity toUserEntity(final UserCreateDTO userDTO);

}
