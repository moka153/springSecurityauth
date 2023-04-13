package com.mokasoft.gestresto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "t_tables")
public class AppTable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;
    @Column(length = 50,unique = true)
    private String tableNumber;
    private int customerNumber;
    private boolean available;
    @ManyToOne
    @JsonIgnore
    private AppUser appUser;
    @ManyToOne
    @JsonIgnore
    private Room room;
    @OneToOne
    @JsonIgnore
    private Sale sale;
}
