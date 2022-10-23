package com.mokasoft.gestresto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String name;
    @OneToMany(mappedBy = "room")
    private List<AppTable> tables;
}
