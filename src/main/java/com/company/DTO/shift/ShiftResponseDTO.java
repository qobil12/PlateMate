package com.company.DTO.shift;

import com.company.Enums.ShiftStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShiftResponseDTO {
    private UUID id;
    private UUID shiftOrderId;
    private UUID waiterId;
    private ShiftStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime respondedAt;
}
