package com.company.Controller;

import com.company.DTO.PasswordChangeDTO;
import com.company.DTO.UserCreateDTO;
import com.company.DTO.UserResponseDTO;
import com.company.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserCreateDTO user) {
        return ResponseEntity.ok().body(userService.registerUser(user));
    }

    @PutMapping("/change_password")
    public ResponseEntity<UserResponseDTO> changeUserPassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        return ResponseEntity.ok().body(userService.changePassword(passwordChangeDTO));
    }
}
