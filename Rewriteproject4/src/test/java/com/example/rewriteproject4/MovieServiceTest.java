package com.example.rewriteproject4;

import com.example.rewriteproject4.Model.Movie;
import com.example.rewriteproject4.Model.User;
import com.example.rewriteproject4.Repository.AuthRepository;
import com.example.rewriteproject4.Repository.MoviesRepository;
import com.example.rewriteproject4.Service.AuthService;
import com.example.rewriteproject4.Service.MoviesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class MovieServiceTest {
    @InjectMocks
    MoviesService moviesService;

    @Mock
    MoviesRepository moviesRepository;

    @Mock
    AuthRepository authRepository;


    Movie movie1;
    Movie movie2;
    User user1;

    Movie movies;

    List<Movie> movieslist;



    @BeforeEach
    void setUp() {
        user1=new User(1,"sh","Sh231","USER",null);
        movie1=new Movie(1,"Dark","serial killer",50,2,2.5,"G",null,null);
        movie2=new Movie(null,"Call","serial killer",50,1,3.5,"R18",null,null);

        moviesRepository.save(movie1);
        moviesRepository.save(movie2);
       movieslist = new ArrayList<>();
       movieslist.add(movie1);
       movieslist.add(movie2);

    }

@Test
    public void getAllMovies(){
        when(moviesRepository.findAll()).thenReturn(movieslist);
        List<Movie>movies=moviesService.getAll();
        Assertions.assertEquals(movies,movieslist);
        verify(moviesRepository,times(1)).findAll();
    }

    @Test
    public void properToKids(){
    when(authRepository.findUserById(user1.getId())).thenReturn(user1);
     when(moviesRepository.kidsmovie()).thenReturn(movieslist);
     List<Movie>movies=moviesService.propertokids(user1.getId());
     Assertions.assertEquals(movies.get(0).getRating(),movie1.getRating());
     verify(authRepository,times(1)).findUserById(user1.getId());
     verify(moviesRepository,times(2)).kidsmovie();
    }

    @Test
    public void offersTest(){
        when(authRepository.findUserById(user1.getId())).thenReturn(user1);
        when(moviesRepository.findMovieByName(movie1.getName())).thenReturn(movie1);
        movies=moviesService.offers(movie1.getName(),user1.getId());
        verify(moviesRepository,times(1)).findMovieByName(movie1.getName());
        verify(authRepository,times(1)).findUserById(user1.getId());

    }





}
