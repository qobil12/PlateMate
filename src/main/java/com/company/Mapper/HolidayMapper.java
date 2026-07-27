package com.company.Mapper;

import com.company.DTO.holiday.HolidayRequestDTO;
import com.company.Entity.Holiday;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class HolidayMapper {
    public abstract HolidayRequestDTO toHolidayResponseDTO(final Holiday holiday);
    public abstract Holiday toHolidayEntity(final HolidayRequestDTO holidayDTO);
}
