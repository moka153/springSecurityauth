package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.RoomRequest;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService1;

    @PostMapping
    public ResponseEntity<Object> saveRoom(@Valid @RequestBody RoomRequest roomRequest){
        return ResponseHandler.responseBuilder("Room created", HttpStatus.CREATED,
                roomService1.saveRoom(roomRequest));
    }
    @PutMapping("/{roomId}")
    public ResponseEntity<Object> updateRoom(@Valid @RequestBody RoomRequest roomRequest,
                                             @PathVariable Long roomId){
        return ResponseHandler.responseBuilder("Room updated", HttpStatus.OK,
                roomService1.updateRoom(roomRequest,roomId));
    }
    @DeleteMapping("{roomId}")
    public void deleteRoom(@PathVariable Long roomId){
        roomService1.deleteRoom(roomId);
    }
    @GetMapping
    public ResponseEntity<Object> getAllRooms(){
        return ResponseEntity.ok().body(roomService1.getAllRooms());
    }
    @GetMapping("/{roomId}")
    public ResponseEntity<Object> getRoom(@PathVariable Long roomId){
        return ResponseEntity.ok().body(roomService1.getRoom(roomId));
    }
}
