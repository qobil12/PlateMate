package com.company.DTO.shift;

import com.company.Entity.Waiter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShiftRequestByDateIntervalDTO {
    private Waiter waiterId;
    private String startDate;
    private String endDate;
}
