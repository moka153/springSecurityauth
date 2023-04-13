package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.RoomRequest;
import com.mokasoft.gestresto.dtos.RoomResponse;

import java.util.List;

public interface RoomService {
    RoomResponse saveRoom(RoomRequest roomRequest);
    RoomResponse updateRoom(RoomRequest roomRequest,Long roomId);
    void deleteRoom(Long roomId);
    List<RoomResponse> getAllRooms();
    RoomResponse getRoom(Long roomId);
}
