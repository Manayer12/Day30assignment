package com.example.rewriteproject4.Service;


import com.example.rewriteproject4.Api.ApiException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor
@Service
public class ViwerService {
    final private ViwerRepository viwerRepository;
    @Autowired
    final private MoviesService moviesService;
    final private MoviesRepository moviesRepository;
    final private RoomsRepoository roomsRepoository;
    final private AuthRepository authRepository;

    public List<Viwer> getAllviwers(Integer user_id) {
        User user=authRepository.findUserById(user_id);
if (user==null){
    throw new ApiException("id is null");
}
        return viwerRepository.findAll();
    }


    public void addViwer(ViwerDTO viwerDTO,Integer user_id) {
        User user=authRepository.findUserById(user_id);
        User user1=authRepository.findUserById(viwerDTO.getUser_id());
        if (user1==null ||user==null){
            throw new ApiException("id is null or wrong");
        }
        Viwer viwer=new Viwer(null,viwerDTO.getName(),viwerDTO.getAge(),null,viwerDTO.getBalance(),user,null,null);
        viwerRepository.save(viwer);
    }


    public void updateViwer(Integer id, ViwerDTO viwerDTO,Integer user_id) {
        User user=authRepository.findUserById(user_id);
        Viwer oldViwer = viwerRepository.findViewerById(id);
        if (oldViwer == null||user==null) {
            throw new ApiException("id not found");
        }
        oldViwer.setName(viwerDTO.getName());
        oldViwer.setAge(viwerDTO.getAge());
        oldViwer.setBalance(viwerDTO.getBalance());
        viwerRepository.save(oldViwer);


    }

    public void deleteViwer(Integer id,Integer user_id) {
        User user=authRepository.findUserById(user_id);
        Viwer oldViwer = viwerRepository.findViewerById(id);
        if (oldViwer == null||user==null) {
            throw new ApiException("id not found");
        }
        viwerRepository.deleteById(id);
    }


    public List<Movie> checkage(Integer id,Integer user_id) {
        User user=authRepository.findUserById(user_id);
        Viwer oldviwer=viwerRepository.findViewerById(id);

        if (oldviwer == null || user==null) {
            throw new ApiException("id not found");
        }

        Integer age = oldviwer.getAge();

        if (age >=18) {
           return moviesService.getAll();
        }

        if (age <15 && age >12) {
            return moviesService.allowedmovies15();
        }

         if (age <12){

            return moviesService.allowedmovies12();
        }

        return moviesService.allowedmovies();

    }





    public void buyticket(Integer id,String name,Integer numofticket,Integer user_id){
        Viwer viwer1=viwerRepository.findViewerById(id);
        User user=authRepository.findUserById(user_id);
        if (viwer1 == null ) {
            throw new ApiException("can not find viwer id");
        } if ( user==null){
            throw new ApiException("can not find user id");
        }



        Movie oldmovie=moviesService.findMovie(name,user.getId());
        if (viwer1.getAge()<12 && !Objects.equals(oldmovie.getRating(), "G") || !Objects.equals(oldmovie.getRating(), "PG")){
            throw new ApiException("movie is not for kids under12");
        }

        else if (viwer1.getAge() <15 && viwer1.getAge() >12 &&!Objects.equals(oldmovie.getRating(), "G") || !Objects.equals(oldmovie.getRating(), "PG")||!Objects.equals(oldmovie.getRating(), "R12")){
        throw new ApiException("movie is not for kids under 15");}

        viwer1.setAmountoftickets(numofticket);
        viwer1.setBalance(viwer1.getBalance()-oldmovie.getTicketprice()*numofticket);
        oldmovie.setChairsamount(oldmovie.getChairsamount()-numofticket);

        viwer1.setMovie(oldmovie);
        viwer1.setRooms(oldmovie.getRooms());

        moviesRepository.save(oldmovie);
        viwerRepository.save(viwer1);
    }

    public void returnticket(Integer id,String name,Integer numofticket,Integer user_id){
        Viwer viwer2=viwerRepository.findViewerById(id);
        User user=authRepository.findUserById(user_id);
        if (viwer2 ==null || viwer2.getAmountoftickets()==null|| user==null ){
            throw new ApiException("can not find viwer id or you dont have ticket to return");
        }

        Movie oldmovie=moviesService.findMovie(name,user_id);
        viwer2.setAmountoftickets(viwer2.getAmountoftickets()-numofticket);
        viwer2.setBalance(viwer2.getBalance()+oldmovie.getTicketprice()*numofticket);
        oldmovie.setChairsamount(oldmovie.getChairsamount()+numofticket);
        viwer2.setMovie(null);
        viwer2.setRooms(null);
        moviesRepository.save(oldmovie);
        viwerRepository.save(viwer2);
    }


    public List<Viwer> VipViwers(Integer user_id){
        User user=authRepository.findUserById(user_id);
        List<Viwer> viwer2=viwerRepository.vipviwers();
    if (viwer2==null || user==null){
        throw new ApiException("no Vip Viwers");
    }
    return viwerRepository.vipviwers();

    }
}









