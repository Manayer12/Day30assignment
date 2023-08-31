package com.example.rewriteproject4.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Viwer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @NotNull
    @Column(columnDefinition = "int not null ")
    private Integer age;

    @NotNull
    @Column(columnDefinition = "int not null ")
    private Integer balance;

    @Column(columnDefinition = "int default 0  ")
    Integer amountoftickets ;



    @OneToOne
    @MapsId
    @JsonIgnore
    User user;


    @ManyToOne
    @JoinColumn(name = "movie_id",referencedColumnName = "id")
    private Movie movie;


    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "movie_id")
    @JsonIgnore
    private Rooms rooms;




}
