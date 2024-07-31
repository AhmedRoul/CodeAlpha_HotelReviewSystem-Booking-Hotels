package com.HotelReviewSystem.Hotel.Review.System.Controllers;

import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import com.HotelReviewSystem.Hotel.Review.System.Repository.JWTBlackListRepository;
import com.HotelReviewSystem.Hotel.Review.System.Repository.UserRepository;
import com.HotelReviewSystem.Hotel.Review.System.Util.JwtUtil;
import com.HotelReviewSystem.Hotel.Review.System.dto.Request.TokenRequest;
import com.HotelReviewSystem.Hotel.Review.System.dto.Resopose.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/V1/")
@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
public class TokenController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    JWTBlackListRepository blackListRepository;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/tokenCheck")
    public ResponseEntity<?> TokenCheck(@RequestBody TokenRequest tokenRequest){

        String token=tokenRequest.getAccessToken();
        if(!jwtUtil.isTokenExpired(token)&&!blackListRepository.existsByToken(token)){
            String email=jwtUtil.extractUsername(token);
            User user=userRepository.findByEmail(email);
            TokenResponse tokenResponse = TokenResponse.builder()
                    .token(token)
                    .role(user.getRole())
                    .email(user.getEmail())
                    .isTokenExpired(false).build();

            return  ResponseEntity.ok().body(tokenResponse);
        }
        else
        {
            TokenResponse tokenResponse=TokenResponse.builder().isTokenExpired(true).build();
            return  ResponseEntity.badRequest().body(tokenResponse);
        }


    }
}
