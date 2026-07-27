package com.company.Service;

import com.company.Entity.Shift;
import com.company.Enums.ShiftStatus;
import com.company.Repository.ShiftRepository;
import com.company.Repository.UserRepository;
import com.company.Repository.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WaiterService {
    private final WaiterRepository waiterRepository;
    private final UserRepository userRepository;
    private final ShiftRepository shiftRepository;


    public List<Shift> getAllApprovedShiftsByWaiterID(UUID waiterId) {
        if (shiftRepository.getAllByWaiterId(waiterId).isEmpty()) {
            throw new RuntimeException("No shift found for waiterId: " + waiterId);
        }
        List<Shift> approvedShifts = shiftRepository.getAllByWaiterId(waiterId).stream()
                .filter(shift -> shift.getStatus().equals(ShiftStatus.APPROVED))
                .toList();
        if (approvedShifts.isEmpty()) {
            throw new RuntimeException("No approved shift found for waiterId: " + waiterId);
        }
        return approvedShifts;
    }

    public List<Shift> getAllShiftsByWaiterID(UUID userId) {
        if (shiftRepository.getAllByWaiterId(userId).isEmpty()) {
            throw new RuntimeException("No shift found for waiterId: " + userId);
        }
        return shiftRepository.getAllByWaiterId(userId);
    }
    public List<Shift> getAllShiftsByWaiterIDAndDate(UUID userId, LocalDate date) {
        if (shiftRepository.findByWaiterIdAndShiftOrderDate(userId, date).isEmpty()) {
            throw new RuntimeException("No shift found for waiterId: " + userId + " and date: " + date);
        }
        return shiftRepository.findByWaiterIdAndShiftOrderDate(userId, date).stream().toList();
    }
}
