package com.example.rewriteproject4.Controller;

import com.example.rewriteproject4.Api.ApiResponse;
import com.example.rewriteproject4.DTO.RoomDTO;
import com.example.rewriteproject4.Model.Rooms;
import com.example.rewriteproject4.Model.User;
import com.example.rewriteproject4.Service.RoomsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomsController {

    private final RoomsService roomsService;

    @GetMapping("/get-all-rooms")
    public ResponseEntity getAll(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(roomsService.getAll(user.getId()));
    }


    @PostMapping("/add-room")
    public ResponseEntity add(@RequestBody @Valid RoomDTO roomDTO, @AuthenticationPrincipal User user){
        roomsService.addRoom(roomDTO,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("room added"));
    }

    @PutMapping("/update-room/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid RoomDTO roomDTO,@AuthenticationPrincipal User user){
       roomsService.updateRoom(id,roomDTO,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("room updated"));
    }

    @DeleteMapping("/delete-room/{id}")
    public ResponseEntity delete(@PathVariable Integer id,@AuthenticationPrincipal User user){
    roomsService.deleteRoom(id,user.getId());
    return ResponseEntity.status(200).body(new ApiResponse("room deleted"));
    }
//get by type
    @GetMapping("/types-of-room/{type}")
    public ResponseEntity fbType(@PathVariable String type,@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(roomsService.findbytype(type,user.getId()));

    }

 //get the unbusy rooms
    @GetMapping("/not-busy-room")
    public ResponseEntity isBusy(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(roomsService.itnotbusy(user.getId()));
    }


    @PutMapping("/change-room-type/{id}")
    public ResponseEntity ctype(@PathVariable Integer id,@AuthenticationPrincipal User user){

        return ResponseEntity.status(200).body(roomsService.changetype(id,user.getId()));


    }

}
