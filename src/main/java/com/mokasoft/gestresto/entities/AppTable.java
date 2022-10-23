package com.mokasoft.gestresto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AppTable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;
    private String tableNumber;
    private int customerNumber;
    private boolean available;
    @ManyToOne
    private AppUser waiter;
    @ManyToOne
    private Room room;
}
