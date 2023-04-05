package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.entities.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail,Long> {
    //@Query("From SaleDetail where sale = :sale")
    List<SaleDetail> findBySale(Sale sale);

}
