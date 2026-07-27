package com.company.DTO.user;

import com.company.DTO.BankDetailsDTO;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phoneNumber;
    private BankDetailsDTO bankDetails;
    private String nin;
}
