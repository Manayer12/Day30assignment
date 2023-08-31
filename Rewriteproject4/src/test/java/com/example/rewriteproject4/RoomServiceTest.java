package com.example.rewriteproject4;

import com.example.rewriteproject4.Model.Movie;
import com.example.rewriteproject4.Model.Rooms;
import com.example.rewriteproject4.Model.User;
import com.example.rewriteproject4.Repository.AuthRepository;
import com.example.rewriteproject4.Repository.RoomsRepoository;
import com.example.rewriteproject4.Service.RoomsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class RoomServiceTest {
    @InjectMocks
    RoomsService roomsService;

    @Mock
    AuthRepository authRepository;

    @Mock
    RoomsRepoository roomsRepoository;


    Rooms room1;
    Rooms room2;

    User user;

    List<Rooms> roomList;

    @BeforeEach
    void setUp() {
        user=new User(1,"sh","Sh231","USER",null);
        room1=new Rooms(null,"Normal",true,null,null);
        room2=new Rooms(null,"Normal",false,null,null);

        roomsRepoository.save(room1);
        roomsRepoository.save(room2);
        roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);

    }
@Test
    public void FindRoomByType(){
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        when(roomsRepoository.findRoomsByType(room1.getType())).thenReturn(roomList);
        roomsService.findbytype(room1.getType(),user.getId());
        Assertions.assertEquals(roomList.get(0).getType(),room1.getType());
        verify(roomsRepoository,times(2)).findRoomsByType(room1.getType());
        verify(authRepository,times(1)).findUserById(user.getId());


    }

    @Test
    public void changeRoomType(){
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        when(roomsRepoository.findRoomsById(room1.getId())).thenReturn(room1);
        roomsService.changetype(room1.getId(),user.getId());
        Assertions.assertEquals(room1.getType(),"Vip");
        verify(roomsRepoository,times(1)).findRoomsById(room1.getId());
        verify(authRepository,times(1)).findUserById(user.getId());




    }
}
