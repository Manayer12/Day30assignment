package com.example.rewriteproject4;

import com.example.rewriteproject4.Api.ApiResponse;
import com.example.rewriteproject4.Controller.MoviesController;
import com.example.rewriteproject4.Model.Movie;
import com.example.rewriteproject4.Model.User;
import com.example.rewriteproject4.Service.MoviesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MoviesController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class MovieControllerTest {
    @MockBean
    MoviesService moviesService;

    @Autowired
    MockMvc mockMvc;

    Movie movie1,movie2;
    User user;

    ApiResponse apiResponse;

    List<Movie> movies,movieList;
    Movie moviesfi;

    @BeforeEach
    void setUp() {
        user=new User(1,"Maha" , "12345" , "ADMIN" , null);
        movie1=new Movie(1,"Dark","serial killer",50,2,2.5,"G",null,null);
        movie2=new Movie(1,"Dark","serial killer",50,2,2.5,"G",null,null);
        movies=Arrays.asList(movie1);
        movieList=Arrays.asList(movie2);
        moviesfi=movie1;

    }

    @Test
    public void GetAllMovies() throws Exception {
        Mockito.when(moviesService.getAll()).thenReturn(movies);
        mockMvc.perform(get("/api/v1/movie/get-all-movie"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Dark"));
    }

    @Test
    public void testAddMovie() throws  Exception {
        mockMvc.perform(post("/api/v1/movie/add-movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(movie1)))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateMovie()throws Exception{
        mockMvc.perform(put("/api/v1/movie/update-movie/{id}",movie1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(movie1)))
                .andExpect(status().isOk());

    }


    @Test
    public void testDeleteMovie() throws Exception{
        mockMvc.perform(delete("/api/v1/movie/delete-movie/{Id}",movie1.getId()))
                .andExpect(status().isOk());

    }

    @Test
    public void GetMovieByName() throws Exception {
        Mockito.when(moviesService.findMovie(movie1.getName(), user.getId())).thenReturn(moviesfi);
        mockMvc.perform(get("/api/v1/movie/find-movie-byname/{name}",movie1.getName()))
                .andExpect(status().isOk());
    }



}
