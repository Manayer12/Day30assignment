package com.example.rewriteproject4.Repository;


import com.example.rewriteproject4.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie,Integer> {

    Movie findMovieById(Integer id);

    Movie findMovieByName(String name);


    @Query("select h from Movie h where h.hours>2")
    List<Movie> movieh();

    @Query("select k from  Movie k where k.rating ='G' or k.rating='PG'")
    List<Movie> kidsmovie();

    @Query("select a from  Movie a where a.rating!='R18'")
    List<Movie> allowedmoviesrunder18();

    @Query("select a from  Movie a where a.rating!='R18' and a.rating!='R15'")
    List<Movie> allowedmoviesunder15();

    @Query("select a from  Movie a where a.rating!='R18'and  a.rating!='R15' and a.rating!='PG15' and a.rating!='R12' ")

    List<Movie> allowedmoviesunder12();




}
