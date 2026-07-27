package com.company.Service;

import com.company.DTO.holiday.HolidayRequestDTO;
import com.company.Entity.Holiday;
import com.company.Enums.HolidayStatus;
import com.company.Mapper.HolidayMapper;
import com.company.Repository.HolidayRepository;
import com.company.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HolidayService {
    final HolidayRepository holidayRepository;
    final UserRepository userRepository;
    final HolidayMapper holidayMapper;

    public HolidayRequestDTO createHolidayRequest(HolidayRequestDTO dto) {
        userRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("User with that id does not exist"));

        if(dto.getStartDate().isAfter(dto.getEndDate()) && dto.getStartDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Start date cannot be after end date");
        }

        Holiday holiday = holidayMapper.toHolidayEntity(dto);
        holidayRepository.save(holiday);

        return holidayMapper.toHolidayResponseDTO(holiday);
    }

    public String declineHolidayRequest(UUID holidayRequestId) {
        Holiday holiday = holidayRepository.findById(holidayRequestId)
                .orElseThrow(() -> new RuntimeException("Holiday request with that id does not exist"));

        holiday.setHolidayStatus(HolidayStatus.DECLINED);
        holidayRepository.save(holiday);

        return "Holiday request declined";
    }

    public String approveHolidayRequest(UUID holidayRequestId) {
        Holiday holiday = holidayRepository.findById(holidayRequestId)
                .orElseThrow(() -> new RuntimeException("Holiday request with that id does not exist"));

        holiday.setHolidayStatus(HolidayStatus.APPROVED);
        holidayRepository.save(holiday);

        return "Holiday request approved";
     }

     public String deleteHolidayRequest(UUID holidayRequestId) {
        Holiday holiday = holidayRepository.findById(holidayRequestId)
                .orElseThrow(() -> new RuntimeException("Holiday request with that id does not exist"));

        holidayRepository.delete(holiday);

        return "Holiday request deleted";
     }
}
