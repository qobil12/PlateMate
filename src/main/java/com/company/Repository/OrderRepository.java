package com.company.Repository;


import com.company.Entity.ShiftOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<ShiftOrder, UUID> {
}
