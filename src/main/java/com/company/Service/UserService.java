package com.company.Service;

import com.company.DTO.PasswordChangeDTO;
import com.company.DTO.UserCreateDTO;
import com.company.DTO.UserResponseDTO;
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
        if (!user.getPassword().equals(dto.getNewPassword())) {
            throw new ItemMismatchException("Old Password Mismatch");
        }

        user.setPassword(dto.getNewPassword());
        return userMapper.toUserResponseDTO(userRepository.save(user));
    }
}
