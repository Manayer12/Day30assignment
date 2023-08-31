package com.example.rewriteproject4.Repository;


import com.example.rewriteproject4.Model.Viwer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViwerRepository extends JpaRepository<Viwer,Integer> {

    Viwer findViewerById(Integer id);

    @Query("select s from Viwer s where s.balance>=1000")
    List<Viwer> vipviwers();











}
