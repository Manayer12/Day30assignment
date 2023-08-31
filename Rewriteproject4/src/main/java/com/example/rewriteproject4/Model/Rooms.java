package com.example.rewriteproject4.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null check(type='Vip' or type='Normal')")
    private String type;


    @NotNull
    @Column(columnDefinition = "boolean not null")
    private Boolean used;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "rooms")
    @JsonIgnore
    private Set<Viwer> viwerSet;


    @OneToOne
    @MapsId
    @JsonIgnore
    Movie movie;
}
