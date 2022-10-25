package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.AppTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppTableRepository extends JpaRepository<AppTable,Long> {
    AppTable findByTableNumber(String tableNumber);
}
