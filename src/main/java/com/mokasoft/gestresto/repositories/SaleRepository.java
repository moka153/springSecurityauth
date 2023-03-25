package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
    Sale findByAppTable(Long tableId);
}
