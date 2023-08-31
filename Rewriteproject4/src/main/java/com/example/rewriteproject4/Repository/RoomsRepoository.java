package com.example.rewriteproject4.Repository;


import com.example.rewriteproject4.Model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepoository extends JpaRepository<Rooms,Integer> {
    Rooms findRoomsById(Integer id);

    List<Rooms> findRoomsByType(String type);

    @Query("select u from Rooms u where u.used=false")
    List<Rooms> busyroom();





}
