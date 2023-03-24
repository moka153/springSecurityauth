package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.Room;
import com.mokasoft.gestresto.entities.Sale;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppTableDto {
    private Long tableId;
    private String tableNumber;
    private int customerNumber;
    private boolean available;
    private RoomDto roomDto;
    private AppUser appUser;
}
