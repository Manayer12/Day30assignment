package com.example.rewriteproject4.Service;


import com.example.rewriteproject4.Api.ApiException;
import com.example.rewriteproject4.DTO.RoomDTO;
import com.example.rewriteproject4.DTO.ViwerDTO;
import com.example.rewriteproject4.Model.Movie;
import com.example.rewriteproject4.Model.Rooms;
import com.example.rewriteproject4.Model.User;
import com.example.rewriteproject4.Model.Viwer;
import com.example.rewriteproject4.Repository.AuthRepository;
import com.example.rewriteproject4.Repository.MoviesRepository;
import com.example.rewriteproject4.Repository.RoomsRepoository;
import com.example.rewriteproject4.Repository.ViwerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class RoomsService {
    final private RoomsRepoository roomsRepoository;
    final private AuthRepository authRepository;
    final private ViwerRepository viwerRepository;
    final private MoviesRepository moviesRepository;

    public List<Rooms> getAll(Integer user_id){
        User user=authRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("id is null");
        }

        return roomsRepoository.findAll();
    }


    public void addRoom(RoomDTO roomDTO, Integer user_id){
        User user=authRepository.findUserById(user_id);
        Movie movie =moviesRepository.findMovieById(roomDTO.getMovie_id());
        if (user==null|| movie==null){
            throw new ApiException("id is null");
        }
       Rooms rooms=new Rooms(null,roomDTO.getType(),roomDTO.getUsed(),null,movie);

        roomsRepoository.save(rooms);
    }


    public void updateRoom(Integer id,RoomDTO roomDTO,Integer user_id){
        User user=authRepository.findUserById(user_id);
        Rooms oldroom=roomsRepoository.findRoomsById(id);
        if(oldroom==null||user==null){
            throw new ApiException("id not found");
        }
       oldroom.setType(roomDTO.getType());
        oldroom.setUsed(roomDTO.getUsed());
        roomsRepoository.save(oldroom);


    }

    public void  deleteRoom(Integer id,Integer user_id){
        Rooms oldRoom=roomsRepoository.findRoomsById(id);
        User user=authRepository.findUserById(user_id);

        if(oldRoom==null|| user==null){
            throw new ApiException("id not found");
        }
        roomsRepoository.deleteById(id);

    }


    public List<Rooms> findbytype(String type,Integer user_id){
        List<Rooms> oldtype=roomsRepoository.findRoomsByType(type);
        User user=authRepository.findUserById(user_id);

        if (oldtype ==null|| user==null){
            throw new ApiException("this type is not in our movies house");
        }
         return  roomsRepoository.findRoomsByType(type);
    }

    public List<Rooms> itnotbusy(Integer user_id){
        List<Rooms> notbusyrooms=roomsRepoository.busyroom();
        User user=authRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("id is null");
        }
        if (notbusyrooms.isEmpty()){
            throw new ApiException("All the rooms is busy");
        }
        return roomsRepoository.busyroom();

    }

    public Rooms changetype(Integer id,Integer user_id){
        Rooms ctype=roomsRepoository.findRoomsById(id);
        User user=authRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("id is null");
        }
        if (ctype==null){
            throw new ApiException("room id not found");
        }
        if (ctype.getType().equals("Normal")){
         ctype.setType("Vip");
         roomsRepoository.save(ctype);
        }
        else {

            ctype.setType("Normal");
            roomsRepoository.save(ctype);

        }

        return ctype;




    }


}
