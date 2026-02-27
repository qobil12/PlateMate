package com.company.DTO;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordChangeDTO {
    private UUID userId;
    private String oldPassword;
    private String newPassword;
}
