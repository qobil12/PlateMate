package com.company.Entity;

import com.company.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Generated
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role= Role.WAITER;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user",orphanRemoval = true)
    private BankDetails bankDetails;

    @Column(nullable = false)
    private String nin;

}
