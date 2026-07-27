package com.company.Service;

import com.company.DTO.*;
import com.company.DTO.user.UserCreateDTO;
import com.company.DTO.user.UserInfoChangeDTO;
import com.company.DTO.user.UserResponseDTO;
import com.company.DTO.user.UserRoleChangeDTO;
import com.company.Entity.UserEntity;
import com.company.Exceptions.ItemAlreadyExistsException;
import com.company.Exceptions.ItemMismatchException;
import com.company.Exceptions.ItemNotFoundException;
import com.company.Mapper.UserMapper;
import com.company.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final UserMapper userMapper;

    public UserResponseDTO registerUser(UserCreateDTO user) {

        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new ItemAlreadyExistsException("User with that email already exists");
        });


        UserEntity userEntity = userMapper.toUserEntity(user);
        if (userEntity.getBankDetails() != null) {
            userEntity.getBankDetails().setUser(userEntity);
        }
        return userMapper.toUserResponseDTO(userRepository.save(userEntity));
    }

    public UserResponseDTO changePassword(PasswordChangeDTO dto){
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new ItemNotFoundException("User with that id does not exist"));
        if (!(user.getPassword().equals(dto.getOldPassword()))) {
            throw new ItemMismatchException("Old Password Mismatch");
        }

        user.setPassword(dto.getNewPassword());
        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

    public UserResponseDTO getUserByEmail(String email){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ItemNotFoundException("User with that email does not exist"));
        return userMapper.toUserResponseDTO(user);
    }

    public UserResponseDTO changeUserPersonalInfo(UserInfoChangeDTO dto){
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new ItemNotFoundException("User with that id does not exist"));
        user.setName(dto.getName() != null ? dto.getName() : user.getName());
        user.setSurname(dto.getSurname() != null ? dto.getSurname() : user.getSurname());
        user.setPhoneNumber(dto.getPhoneNumber() != null ? dto.getPhoneNumber() : user.getPhoneNumber());
        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

    public UserResponseDTO userRoleChange(UserRoleChangeDTO dto){
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new ItemNotFoundException("User with that email does not exist"));
        user.setRole(dto.getNewRole());
        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

}
