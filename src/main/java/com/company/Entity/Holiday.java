package com.company.Entity;

import com.company.Enums.HolidayStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Holiday {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private HolidayStatus holidayStatus= HolidayStatus.PENDING;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Waiter waiter;

    @Column(nullable = false)
    private Integer durationInDays;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
