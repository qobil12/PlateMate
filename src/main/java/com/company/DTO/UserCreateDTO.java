package com.company.DTO;

import com.company.Entity.BankDetails;
import com.company.Enums.Role;
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
