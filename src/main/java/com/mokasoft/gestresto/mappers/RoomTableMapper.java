package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.AppTableDto;
import com.mokasoft.gestresto.dtos.RoomDto;
import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.Room;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoomTableMapper {
    public RoomDto fromRoom(Room room){
        RoomDto roomDto = new RoomDto();
        BeanUtils.copyProperties(room,roomDto);
        return roomDto;
    }

    public Room fromRoomDto(RoomDto roomDto){
        Room room = new Room();
        BeanUtils.copyProperties(roomDto,room);
        return room;
    }

    public AppTableDto fromAppTable(AppTable appTable){
        AppTableDto appTableDto = new AppTableDto();
        BeanUtils.copyProperties(appTable,appTableDto);
        appTableDto.setRoomDto(fromRoom(appTable.getRoom()));
        return appTableDto;
    }

    public AppTable fromAppTableDto(AppTableDto appTableDto){
        AppTable appTable = new AppTable();
        BeanUtils.copyProperties(appTableDto,appTable);
        appTable.setRoom(fromRoomDto(appTableDto.getRoomDto()));
        return appTable;
    }
}
