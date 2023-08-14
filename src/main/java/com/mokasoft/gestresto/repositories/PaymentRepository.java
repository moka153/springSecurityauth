package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.Payment;
import com.mokasoft.gestresto.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findBySale(Sale sale);
}
