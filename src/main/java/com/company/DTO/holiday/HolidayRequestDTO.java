package com.company.DTO.holiday;

import com.company.Enums.HolidayStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HolidayRequestDTO {
    private UUID employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private HolidayStatus status= HolidayStatus.PENDING;
}
