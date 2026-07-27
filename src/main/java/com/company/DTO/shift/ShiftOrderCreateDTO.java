package com.company.DTO.shift;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShiftOrderCreateDTO {
    private Integer numberOfStaff;
    private Double payRate;
    private LocalDateTime startTime;
    private LocalDate date;
    private LocalDateTime endTime;
    private LocalDateTime duration;
    private String description;
}
