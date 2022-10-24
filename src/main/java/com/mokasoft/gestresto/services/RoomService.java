package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.AppTableDto;
import com.mokasoft.gestresto.dtos.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto saveRoom(RoomDto roomDto);
    List<RoomDto> getRooms();
    AppTableDto saveTable(AppTableDto appTableDto);

    void affectWaiterToTable(String waiterName,String tableName);

}