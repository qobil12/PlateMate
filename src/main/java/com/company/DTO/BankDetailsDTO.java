package com.company.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDetailsDTO {
    private String sortCode;
    private String accountNumber;
    private String bankName;
}
