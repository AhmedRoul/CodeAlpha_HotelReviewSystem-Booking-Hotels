package com.HotelReviewSystem.Hotel.Review.System.Controllers;

import com.HotelReviewSystem.Hotel.Review.System.Util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"},
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = RequestMethod.POST)
@RestController
@RequestMapping("api/V1/")
@Slf4j

public class LogoutController {
    @Autowired
    JwtUtil jwtUtil;
    @PostMapping("/logout")
    public ResponseEntity<?> Logout(@RequestHeader("Authorization") String authorizationHeader){

        String token=authorizationHeader.substring(7);

        if(jwtUtil.SetBlockToken(token)) {
            return ResponseEntity.ok("Done!!");
        }
        else {
            return ResponseEntity.badRequest().body("it didn't logout");
        }
    }
}
