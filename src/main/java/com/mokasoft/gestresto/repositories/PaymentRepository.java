package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
