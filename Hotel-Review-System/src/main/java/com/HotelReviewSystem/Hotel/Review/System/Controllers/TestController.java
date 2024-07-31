package com.HotelReviewSystem.Hotel.Review.System.Controllers;

import com.HotelReviewSystem.Hotel.Review.System.Config.Password.CustomPasswordEncoder;
import com.HotelReviewSystem.Hotel.Review.System.Model.Admin;
import com.HotelReviewSystem.Hotel.Review.System.Model.Customer;
import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import com.HotelReviewSystem.Hotel.Review.System.Repository.AdminRepository;
import com.HotelReviewSystem.Hotel.Review.System.Repository.CustomerRepository;
import com.HotelReviewSystem.Hotel.Review.System.Repository.UserRepository;
import com.HotelReviewSystem.Hotel.Review.System.Util.Countrys;
import com.HotelReviewSystem.Hotel.Review.System.dto.Request.AdminRegisterRequest;
import com.HotelReviewSystem.Hotel.Review.System.dto.Resopose.RegisterResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1/add")
@Slf4j
public class TestController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    CustomPasswordEncoder passwordEncoder;
    @PostMapping
    public ResponseEntity<?> addAdmin(@Valid @RequestBody AdminRegisterRequest request){

        if(userRepository.existsByEmail(request.getEmail())){

            RegisterResponse registerResponse = RegisterResponse.builder().email("Email already exists").build();

            return ResponseEntity.badRequest().body(registerResponse);
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        User user=userRepository.save(request.GetUser());
        Admin admin=request.GetAdmin(user);
        Admin admins= adminRepository.save(admin);
        return  ResponseEntity.ok("done"+admins.toString());
    }
}
