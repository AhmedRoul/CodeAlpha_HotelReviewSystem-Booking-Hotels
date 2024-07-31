package com.HotelReviewSystem.Hotel.Review.System.Controllers;

import com.HotelReviewSystem.Hotel.Review.System.dto.Request.UserResignPasswordRequest;
import com.HotelReviewSystem.Hotel.Review.System.implement.InfoUser;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/V1/User")
@CrossOrigin(origins = {"http://localhost:4200"})
@Slf4j
public class UserController {
    @Autowired
    InfoUser infoUser;
    @PutMapping
    @RolesAllowed({"Admin", "Customer"})
    public ResponseEntity<?> ChangePassword(@RequestHeader("Authorization") String authorizationHeader,
                                            @Valid @RequestBody UserResignPasswordRequest request){
        if(Objects.equals(request.getPassword(), request.getNewPassword()))
        {
            ResponseEntity.badRequest().body("Current password same new password Rewrite new Password");
        }
        if(infoUser.ChangePassword(authorizationHeader,request.getPassword(),request.getNewPassword()))
            return ResponseEntity.ok("Done Changed");

        else
            return ResponseEntity.badRequest().body("Current password not correct ");

    }


}
