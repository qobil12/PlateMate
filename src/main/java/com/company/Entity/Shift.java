package com.company.Entity;

import com.company.Enums.ShiftStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity

@NoArgsConstructor
@Getter
@Setter
public class Shift  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "shift_order_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ShiftOrder shiftOrder;

    @JoinColumn(name="waiter_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Waiter waiter;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShiftStatus status;

    @Column(nullable = false)
    private LocalDateTime requestedAt;

    @Column(nullable = false)
    private LocalDateTime respondedAt;

}
