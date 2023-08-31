package com.example.rewriteproject4.Controller;


import com.example.rewriteproject4.Api.ApiResponse;
import com.example.rewriteproject4.Model.Movie;
import com.example.rewriteproject4.Model.User;
import com.example.rewriteproject4.Service.MoviesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MoviesController {
    private final MoviesService moviesService;

    @GetMapping("/get-all-movie")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(moviesService.getAll());
    }


    @PostMapping("/add-movie")
    public ResponseEntity add(@RequestBody @Valid Movie movie,@AuthenticationPrincipal User user){
        moviesService.addMovie(movie,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("movie added"));
    }


    @PutMapping("/update-movie/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody @Valid Movie movie,@AuthenticationPrincipal User user){
        moviesService.updateMovie(id, movie,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("movie updated"));
    }

    @DeleteMapping("/delete-movie/{id}")
    public ResponseEntity delete(@PathVariable Integer id,@AuthenticationPrincipal User user){
        moviesService.deleteMovie(id,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("movie deleted"));
    }
   // return movie by name
    @GetMapping("/find-movie-byname/{name}")
    public ResponseEntity find(@PathVariable String name,@AuthenticationPrincipal User user){

        return ResponseEntity.status(200).body(moviesService.findMovie(name, user.getId()));
    }

    //return movies which is more than two h duration
    @GetMapping("/find-houres-of-movie")
    public ResponseEntity findh(@AuthenticationPrincipal User user){

        return ResponseEntity.status(200).body(moviesService.findbyhoures(user.getId()));
    }

    @GetMapping("/kids-movies")
    public ResponseEntity kidsmovies(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(moviesService.propertokids(user.getId()));
    }

//apply 10% offers on movie ticket
    @GetMapping("/offers/{name}")
    private ResponseEntity offers(@PathVariable String name,@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(moviesService.offers(name,user.getId()));



    }



}
