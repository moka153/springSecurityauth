package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.Room;
import com.mokasoft.gestresto.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppTableRepository extends JpaRepository<AppTable,Long> {
    AppTable findByTableNumber(String tableNumber);
    List<AppTable> findByAppUser(AppUser userName);
    List<AppTable>findByRoom(Room roomId);
    @Modifying
    @Transactional
    @Query("update AppTable set available = :available , customerNumber= :customerNumber" +
            ",sale=:sale where tableId = :tableId")
    void availableTable(Long tableId, boolean available, int customerNumber, Sale sale);

}
