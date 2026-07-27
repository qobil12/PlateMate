package com.company.Service;

import com.company.DTO.shift.ShiftCreateDTO;
import com.company.DTO.shift.ShiftResponseDTO;
import com.company.Entity.Shift;
import com.company.Entity.ShiftOrder;
import com.company.Entity.Waiter;
import com.company.Enums.ShiftStatus;
import com.company.Exceptions.ItemNotFoundException;
import com.company.Mapper.ShiftMapper;
import com.company.Repository.ShiftOrderRepository;
import com.company.Repository.ShiftRepository;
import com.company.Repository.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final ShiftOrderRepository shiftOrderRepository;
    private final WaiterRepository waiterRepository;
    private final ShiftMapper shiftMapper;

    // ---------- ADD ----------
    public ShiftResponseDTO createShift(ShiftCreateDTO dto) {
        ShiftOrder shiftOrder = shiftOrderRepository.findById(dto.getShiftOrderId())
                .orElseThrow(() -> new ItemNotFoundException("Shift order with that id does not exist"));
        Waiter waiter = waiterRepository.findById(dto.getWaiterId())
                .orElseThrow(() -> new ItemNotFoundException("Waiter with that id does not exist"));

        Shift shift = new Shift();
        shift.setShiftOrder(shiftOrder);
        shift.setWaiter(waiter);
        shift.setStatus(dto.getStatus() != null ? dto.getStatus() : ShiftStatus.PENDING);
        shift.setRequestedAt(LocalDateTime.now());
        shift.setRespondedAt(LocalDateTime.now());

        return shiftMapper.toShiftResponseDTO(shiftRepository.save(shift));
    }

    // ---------- BROWSE ----------
    public List<ShiftResponseDTO> getAllShifts() {
        return shiftRepository.findAll().stream()
                .map(shiftMapper::toShiftResponseDTO)
                .toList();
    }

    // ---------- READ ----------
    public ShiftResponseDTO getShiftById(UUID shiftId) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ItemNotFoundException("Shift with that id does not exist"));

        return shiftMapper.toShiftResponseDTO(shift);
    }

    // ---------- EDIT ----------
    public ShiftResponseDTO updateShift(UUID shiftId, ShiftCreateDTO dto) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ItemNotFoundException("Shift with that id does not exist"));

        if (dto.getShiftOrderId() != null) {
            ShiftOrder shiftOrder = shiftOrderRepository.findById(dto.getShiftOrderId())
                    .orElseThrow(() -> new ItemNotFoundException("Shift order with that id does not exist"));
            shift.setShiftOrder(shiftOrder);
        }
        if (dto.getWaiterId() != null) {
            Waiter waiter = waiterRepository.findById(dto.getWaiterId())
                    .orElseThrow(() -> new ItemNotFoundException("Waiter with that id does not exist"));
            shift.setWaiter(waiter);
        }
        if (dto.getStatus() != null) {
            shift.setStatus(dto.getStatus());
        }
        shift.setRespondedAt(LocalDateTime.now());

        return shiftMapper.toShiftResponseDTO(shiftRepository.save(shift));
    }

    // ---------- DELETE ----------
    public String deleteShift(UUID shiftId) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ItemNotFoundException("Shift with that id does not exist"));

        shiftRepository.delete(shift);

        return "Shift deleted";
    }
}
