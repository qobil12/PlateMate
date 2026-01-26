package com.company.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Waiter extends UserEntity {

    @JoinColumn(nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private BankDetails bankDetails;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private String NIN;

    @Column(nullable = false)
    private Long workedHours;

    @JoinColumn(nullable = false)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Shift> workedShifts;

    @Column(nullable = false)
    private Boolean isOnHoliday;

    @Column(nullable = false)
    private Boolean isBusy;


}
