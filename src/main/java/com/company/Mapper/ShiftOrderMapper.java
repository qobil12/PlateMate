package com.company.Mapper;

import com.company.DTO.shift.ShiftOrderCreateDTO;
import com.company.DTO.shift.ShiftOrderResponseDTO;
import com.company.Entity.ShiftOrder;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class ShiftOrderMapper {
    public abstract ShiftOrder toShiftOrderEntity(final ShiftOrderCreateDTO dto);

    public abstract ShiftOrderResponseDTO toShiftOrderResponseDTO(final ShiftOrder shiftOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateShiftOrderFromDTO(final ShiftOrderCreateDTO dto, final @MappingTarget ShiftOrder shiftOrder);
}
