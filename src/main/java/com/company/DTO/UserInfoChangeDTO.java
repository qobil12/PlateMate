package com.company.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoChangeDTO {
    private UUID userId;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phoneNumber;
}
