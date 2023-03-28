package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.Room;
import com.mokasoft.gestresto.entities.Sale;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppTableDto {
    //private Long tableId;
    private String tableNumber;
    private int customerNumber;
    private boolean available;
    //private Room room;
    //private AppUser appUser;
    //private Sale sale;
    private Long saleId;
}
