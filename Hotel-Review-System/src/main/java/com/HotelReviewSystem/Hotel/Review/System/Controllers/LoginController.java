package com.HotelReviewSystem.Hotel.Review.System.Controllers;

import com.HotelReviewSystem.Hotel.Review.System.Config.Password.CustomPasswordEncoder;
import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import com.HotelReviewSystem.Hotel.Review.System.Repository.UserRepository;
import com.HotelReviewSystem.Hotel.Review.System.Util.JwtUtil;
import com.HotelReviewSystem.Hotel.Review.System.dto.Request.LoginReqeust;
import com.HotelReviewSystem.Hotel.Review.System.dto.Resopose.JwtResponseDTO;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/V1/login")
@Slf4j
@CrossOrigin(origins = {"http://localhost:4200",} ,allowedHeaders = {"*"}, exposedHeaders = {"Set-Cookie"})
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtils;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqeust loginRequest){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        if(authentication.isAuthenticated()){

                String token = jwtUtils.generateToken(loginRequest.GetUser());
                Cookie cookie = new Cookie("JWT-TOKEN", token);
                cookie.setMaxAge(3600*60); // 1 hour
                cookie.setHttpOnly(true);
                cookie.setSecure(true);

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE,cookie.toString())
                    .body( JwtResponseDTO.builder()
                    .accessToken(token).build());
        } else {

            //throw new UsernameNotFoundException("invalid user request..!!");
            return  ResponseEntity.badRequest()
                    .body( "invalid user request..!!  email or password warn");
        }

    }

}
