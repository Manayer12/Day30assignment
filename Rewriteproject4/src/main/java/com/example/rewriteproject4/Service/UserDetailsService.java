package com.example.rewriteproject4.Service;


import com.example.rewriteproject4.Api.ApiException;
import com.example.rewriteproject4.Model.User;
import com.example.rewriteproject4.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
   private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=authRepository.findUserByUsername(username);
        if (user==null){

            throw new ApiException("Wrong user name or password");
        }
        return user;
    }
}
