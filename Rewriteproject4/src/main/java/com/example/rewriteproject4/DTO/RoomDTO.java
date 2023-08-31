package com.example.rewriteproject4.DTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Valid
public class RoomDTO {

    private Integer movie_id;


    private String type;


    private Boolean used;

}
