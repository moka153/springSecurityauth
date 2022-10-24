package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.Room;
import lombok.Data;

@Data
public class AppTableDto {
    private Long tableId;
    private String tableNumber;
    private int customerNumber;
    private boolean available;
    private RoomDto roomDto;
}
