package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.RoomRequest;
import com.mokasoft.gestresto.dtos.RoomResponse;
import com.mokasoft.gestresto.entities.Room;
import com.mokasoft.gestresto.exceptions.ConflictException;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.RoomMapper;
import com.mokasoft.gestresto.repositories.RoomRepository;
import com.mokasoft.gestresto.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    @Override
    public RoomResponse saveRoom(RoomRequest roomRequest) {
        try{
            Room room = roomMapper.roomRequestToRoom(roomRequest);
            Room savedRoom = roomRepository.save(room);
            return roomMapper.roomToRoomResponse(savedRoom);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("room already exists");
        }
    }

    @Override
    public RoomResponse updateRoom(RoomRequest roomRequest, Long roomId) {
        if(!roomRepository.findById(roomId).isPresent()){
            throw new NotFoundException("Room with id: "+roomId+" not found");
        }
        try{
            Room room = roomMapper.roomRequestToRoom(roomRequest);
            room.setRoomId(roomId);
            Room savedRoom = roomRepository.save(room);
            return roomMapper.roomToRoomResponse(savedRoom);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("room already exists");
        }
    }

    @Override
    public void deleteRoom(Long roomId) {
        if(!roomRepository.findById(roomId).isPresent()){
            throw new NotFoundException("Room with id: "+roomId+" not found");
        }
        try{
            roomRepository.deleteById(roomId);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("Room can't be deleted, room already used");
        }
    }

    @Override
    public List<RoomResponse> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomResponse> roomResponses = rooms.stream()
                .map(room -> roomMapper.roomToRoomResponse(room))
                .collect(Collectors.toList());
        return roomResponses;
    }

    @Override
    public RoomResponse getRoom(Long roomId) {
        if(!roomRepository.findById(roomId).isPresent()){
            throw new NotFoundException("Room with id: "+roomId+" not found");
        }
        return roomMapper
                .roomToRoomResponse(roomRepository.findById(roomId).get());
    }
}
