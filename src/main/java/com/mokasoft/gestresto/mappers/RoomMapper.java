package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.RoomRequest;
import com.mokasoft.gestresto.dtos.RoomResponse;
import com.mokasoft.gestresto.entities.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public Room roomRequestToRoom(RoomRequest roomRequest){
        Room room = Room.builder()
                .name(roomRequest.getName())
                .build();
        return room;
    }

    public RoomResponse roomToRoomResponse(Room room){
        RoomResponse roomResponse = RoomResponse.builder()
                .roomId(room.getRoomId())
                .name(room.getName())
                .build();
        return roomResponse;
    }
}
