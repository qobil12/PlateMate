package com.company.Repository;

import com.company.Entity.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WaiterRepository extends JpaRepository<Waiter, UUID> {
}
