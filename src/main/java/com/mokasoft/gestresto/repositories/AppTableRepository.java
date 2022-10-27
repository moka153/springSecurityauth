package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppTableRepository extends JpaRepository<AppTable,Long> {
    AppTable findByTableNumber(String tableNumber);
    List<AppTable> findByAppUser(AppUser userName);
}
