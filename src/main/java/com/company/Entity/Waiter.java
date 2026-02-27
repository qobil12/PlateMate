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
public class Waiter extends UserEntity {

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private Boolean isOnHoliday;

    @Column(nullable = false)
    private Boolean isBusy;

    @OneToMany(mappedBy = "waiter", fetch = FetchType.LAZY)
    private List<Shift> shifts;

}
