package com.company.Service;

import com.company.DTO.shift.ShiftOrderCreateDTO;
import com.company.DTO.shift.ShiftOrderResponseDTO;
import com.company.Entity.ShiftOrder;
import com.company.Exceptions.BadRequestException;
import com.company.Exceptions.ItemNotFoundException;
import com.company.Mapper.ShiftOrderMapper;
import com.company.Repository.ShiftOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShiftOrderService {

    private final ShiftOrderRepository shiftOrderRepository;
    private final ShiftOrderMapper shiftOrderMapper;

    // ---------- ADD ----------
    public ShiftOrderResponseDTO createShiftOrder(ShiftOrderCreateDTO dto) {
        if (dto.getStartTime() != null && dto.getEndTime() != null
                && dto.getStartTime().isAfter(dto.getEndTime())) {
            throw new BadRequestException("Start time cannot be after end time");
        }

        ShiftOrder shiftOrder = shiftOrderMapper.toShiftOrderEntity(dto);
        LocalDateTime now = LocalDateTime.now();
        shiftOrder.setCreatedAt(now);
        shiftOrder.setUpdatedAt(now);

        return shiftOrderMapper.toShiftOrderResponseDTO(shiftOrderRepository.save(shiftOrder));
    }

    // ---------- BROWSE ----------
    public List<ShiftOrderResponseDTO> getAllShiftOrders() {
        return shiftOrderRepository.findAll().stream()
                .map(shiftOrderMapper::toShiftOrderResponseDTO)
                .toList();
    }

    // ---------- READ ----------
    public ShiftOrderResponseDTO getShiftOrderById(UUID shiftOrderId) {
        ShiftOrder shiftOrder = shiftOrderRepository.findById(shiftOrderId)
                .orElseThrow(() -> new ItemNotFoundException("Shift order with that id does not exist"));

        return shiftOrderMapper.toShiftOrderResponseDTO(shiftOrder);
    }

    // ---------- EDIT ----------
    public ShiftOrderResponseDTO updateShiftOrder(UUID shiftOrderId, ShiftOrderCreateDTO dto) {
        ShiftOrder shiftOrder = shiftOrderRepository.findById(shiftOrderId)
                .orElseThrow(() -> new ItemNotFoundException("Shift order with that id does not exist"));

        shiftOrderMapper.updateShiftOrderFromDTO(dto, shiftOrder);

        if (shiftOrder.getStartTime() != null && shiftOrder.getEndTime() != null
                && shiftOrder.getStartTime().isAfter(shiftOrder.getEndTime())) {
            throw new BadRequestException("Start time cannot be after end time");
        }

        shiftOrder.setUpdatedAt(LocalDateTime.now());

        return shiftOrderMapper.toShiftOrderResponseDTO(shiftOrderRepository.save(shiftOrder));
    }

    // ---------- DELETE ----------
    public String deleteShiftOrder(UUID shiftOrderId) {
        ShiftOrder shiftOrder = shiftOrderRepository.findById(shiftOrderId)
                .orElseThrow(() -> new ItemNotFoundException("Shift order with that id does not exist"));

        shiftOrderRepository.delete(shiftOrder);

        return "Shift order deleted";
    }
}
