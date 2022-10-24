package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.AppTable;
import lombok.Data;

import java.util.List;
@Data
public class RoomDto {
    private Long roomId;
    private String name;
    //private List<AppTable> tables;
}
