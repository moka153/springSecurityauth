package com.mokasoft.gestresto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mokasoft.gestresto.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "t_users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private UserStatus userStatus ;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> appRoles ;
    @OneToMany(mappedBy = "appUser")
    @JsonIgnore
    private List<AppTable> tables;
    @OneToMany(mappedBy = "appUser")
    @JsonIgnore
    private List<Sale> sales ;
}
