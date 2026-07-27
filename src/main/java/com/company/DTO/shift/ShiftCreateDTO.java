package com.company.DTO.shift;

import com.company.Enums.ShiftStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShiftCreateDTO {
    private UUID shiftOrderId;
    private UUID waiterId;
    private ShiftStatus status;
}
