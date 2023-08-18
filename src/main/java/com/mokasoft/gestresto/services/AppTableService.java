package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.AppTableRequest;
import com.mokasoft.gestresto.dtos.AppTableResponse;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.Room;

import java.util.List;

public interface AppTableService {
    AppTableResponse saveTable(AppTableRequest appTableRequest);
    AppTableResponse updateTable(AppTableRequest appTableRequest,Long tableId);
    void deleteTable(Long tableId);
    List<AppTableResponse> getAllTables(Room room);

    AppTableResponse getTable(Long tableId);
    List<AppTableResponse> getAllTablesByUser(AppUser userId);
    void affectTableToUser(String userName,String tableName);

}
