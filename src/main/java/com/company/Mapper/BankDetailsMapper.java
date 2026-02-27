package com.company.Mapper;

import com.company.DTO.BankDetailsDTO;
import com.company.Entity.BankDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankDetailsMapper {
    BankDetails toEntity(BankDetailsDTO dto);
    BankDetailsDTO toDto(BankDetails entity);
}
