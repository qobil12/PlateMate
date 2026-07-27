package com.company.Mapper;

import com.company.DTO.shift.ShiftResponseDTO;
import com.company.Entity.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ShiftMapper {
    @Mapping(target = "shiftOrderId", source = "shiftOrder.id")
    @Mapping(target = "waiterId", source = "waiter.id")
    public abstract ShiftResponseDTO toShiftResponseDTO(final Shift shift);
}
