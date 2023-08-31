package com.example.rewriteproject4;

import com.example.rewriteproject4.Model.Movie;
import com.example.rewriteproject4.Repository.MoviesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest {
    @Autowired
    MoviesRepository moviesRepository;


    Movie movie1;
    Movie movie2;

    Movie movies;

    List<Movie> movieList;

    @BeforeEach
    void setUp() {
        movie1=new Movie(1,"Dark","serial killer",50,2,2.5,"R18",null,null);
        movie2=new Movie(null,"Call","serial killer",50,1,3.5,"R18",null,null);
      moviesRepository.save(movie1);
      moviesRepository.save(movie2);

    }

@Test
    public void findMovieByName(){
    movies=moviesRepository.findMovieByName(movie1.getName());
    Assertions.assertThat(movies.getName()).isEqualTo(movie1.getName());

    }
@Test
    public void findByH(){
        movieList=moviesRepository.movieh();
        Assertions.assertThat(movieList.get(0).getHours()).isGreaterThan(2);

    }


    @Test
    public void findByRatingunderR18(){
        movieList=moviesRepository.allowedmoviesrunder18();
        Assertions.assertThat(movieList.get(0).getRating()).isNotEqualTo(("R18"));

    }


}
