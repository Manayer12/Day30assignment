package com.example.rewriteproject4.Configration;

import com.example.rewriteproject4.Service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {
    private final UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/rigster").permitAll()
                .requestMatchers("/api/v1/rooms/get-all-rooms","/api/v1/movie/get-all-movie","/api/v1/movie/find-movie-byname/{name}","/api/v1/movie/find-houres-of-movie","/api/v1/movie/kids-movies").permitAll()
                .requestMatchers("/api/v1/movie/offers/{name}","/api/v1/movie/delete-movie/{id}","/api/v1/movie/update-movie/{id}","/api/v1/movie/add-movie/{movie_id}/{room_id}","/api/v1/viwer/get-all-viwers", "/api/v1/rooms/change-room-type/{id}", "/api/v1/rooms/not-busy-room", "/api/v1/rooms/add-room","/api/v1/viwer/vip", "/api/v1/rooms/update-room/{id}", "/api/v1/rooms/delete-room/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/viwer/add-viwer","/api/v1/rooms/types-of-room/{type}", "/api/v1/viwer/update-viwer/{id}", "/api/v1/viwer/delete-viwer/{id}","/api/v1/viwer/listbasedage/{id}", "/api/v1/viwer/buy-ticket/{id}/{moviename}/{numofticket}", "/api/v1/viwer/return-ticket/{id}/{moviename}/{numofticket}").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}
