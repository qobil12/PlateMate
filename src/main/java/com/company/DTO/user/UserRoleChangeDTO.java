package com.company.DTO.user;

import com.company.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleChangeDTO {
    private UUID userId;
    private Role newRole;
}
