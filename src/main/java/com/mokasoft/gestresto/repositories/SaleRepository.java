package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SaleRepository extends JpaRepository<Sale,Long> {
    Sale findByAppTable(Long tableId);
    @Modifying
    @Transactional
    @Query("update Sale set saleStatus = 'VALIDATED' where saleId = :saleId")
    void saleValidation(Long saleId);
}
