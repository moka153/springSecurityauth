package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.AppTableRequest;
import com.mokasoft.gestresto.dtos.AppTableResponse;
import com.mokasoft.gestresto.dtos.RoomRequest;
import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.Room;
import org.springframework.stereotype.Component;

@Component
public class AppTableMapper {
    public AppTable appTableRequestToAppTable(AppTableRequest appTableRequest){
        Room room = new Room();
        room.setRoomId(appTableRequest.getRoomId());
        AppTable appTable = AppTable.builder()
                .tableNumber(appTableRequest.getTableNumber())
                .customerNumber(appTableRequest.getCustomerNumber())
                .available(appTableRequest.isAvailable())
                .room(room)
                .build();
        return appTable;
    }
    public AppTableResponse appTableToAppTableResponse(AppTable appTable){
        Long saleId = null;
        if(appTable.getSale() != null){
            saleId = appTable.getSale().getSaleId();
        }
        AppTableResponse appTableResponse = AppTableResponse.builder()
                .tableId(appTable.getTableId())
                .tableNumber(appTable.getTableNumber())
                .customerNumber(appTable.getCustomerNumber())
                .available(appTable.isAvailable())
                .roomResponse(new RoomMapper().roomToRoomResponse(appTable.getRoom()))
                .saleId(saleId)
                .build();
        return appTableResponse;
    }
}
