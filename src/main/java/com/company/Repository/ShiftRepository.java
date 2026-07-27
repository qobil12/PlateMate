package com.company.Repository;

import com.company.Entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShiftRepository extends JpaRepository<Shift, UUID> {
    List<Shift> getAllByWaiterId(UUID waiterId);

    Optional<Shift> findByWaiterIdAndShiftOrderDate(UUID waiter_id, LocalDate date);
}
