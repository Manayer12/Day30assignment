package com.example.rewriteproject4.Controller;


import com.example.rewriteproject4.Api.ApiResponse;
import com.example.rewriteproject4.DTO.ViwerDTO;
import com.example.rewriteproject4.Model.User;
import com.example.rewriteproject4.Model.Viwer;
import com.example.rewriteproject4.Service.ViwerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/viwer")
public class ViwerController {

private final ViwerService viwerService;
    @GetMapping("/get-all-viwers")
    public ResponseEntity getAll(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(viwerService.getAllviwers(user.getId()));
    }

    @PostMapping("/add-viwer")
    public ResponseEntity add(@RequestBody @Valid ViwerDTO viwerDTO ,@AuthenticationPrincipal User user){
        viwerService.addViwer(viwerDTO,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("viwer added"));
    }

    @PutMapping("/update-viwer/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid  ViwerDTO viwerDTO,@AuthenticationPrincipal User user){
        viwerService.updateViwer(id,viwerDTO,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("viwer updated"));
    }

    @DeleteMapping("/delete-viwer/{id}")
    public ResponseEntity delete(@PathVariable Integer id,@AuthenticationPrincipal User user){
        viwerService.deleteViwer(id,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("viwer deleted"));
    }

 //depending on the age of the viwer it will show  movies for them
    @GetMapping("/list-based-age/{id}")
    public ResponseEntity checkage(@PathVariable Integer id,@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(viwerService.checkage(id,user.getId()));

    }

    @GetMapping("/buy-ticket/{id}/{moviename}/{numofticket}")
    public ResponseEntity checkage(@PathVariable Integer id,@PathVariable String moviename,@PathVariable Integer numofticket,@AuthenticationPrincipal User user){
        viwerService.buyticket(id,moviename,numofticket,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("your ticket is purchased,thank you"));

    }

    @GetMapping("return-ticket/{id}/{moviename}/{numofticket}")
    public ResponseEntity returntickets(@PathVariable Integer id,@PathVariable String moviename,@PathVariable Integer numofticket,@AuthenticationPrincipal User user) {
        viwerService.returnticket(id,moviename,numofticket,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("your purchased is returned,thank you"));

    }

        @GetMapping("/vip")
    private ResponseEntity vipviwers(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(viwerService.VipViwers(user.getId()));
    }





}
