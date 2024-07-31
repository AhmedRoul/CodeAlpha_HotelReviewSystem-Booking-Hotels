package com.HotelReviewSystem.Hotel.Review.System.implement;

import com.HotelReviewSystem.Hotel.Review.System.Config.Password.CustomPasswordEncoder;
import com.HotelReviewSystem.Hotel.Review.System.Model.Admin;
import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import com.HotelReviewSystem.Hotel.Review.System.Repository.AdminRepository;
import com.HotelReviewSystem.Hotel.Review.System.Repository.UserRepository;
import com.HotelReviewSystem.Hotel.Review.System.Util.JwtUtil;
import com.HotelReviewSystem.Hotel.Review.System.dto.Request.AdminUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class InfoUser {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    CustomPasswordEncoder passwordEncoder;
    public  User getAdmin(String authorizationHeader){
        String email=jwtUtil.extractUsername(authorizationHeader.substring(7));
        User user=userRepository.findByEmail(email);

        return user;
    }
    public  boolean ChangePassword(String authorizationHeader, String Password,String NewPassword){
        String email=jwtUtil.extractUsername(authorizationHeader.substring(7));
        User user=userRepository.findByEmail(email);
        boolean CorrectPassword= passwordEncoder.matches(Password,user.getPassword());
        if(CorrectPassword){
            user.setPassword(NewPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<Admin> getAdmins(){

        List<Admin> admins= adminRepository.findAll();

        admins.forEach(admin -> admin.getUser().setAdmin(null));
        return admins;
    }
    public boolean UpdateAdmin(AdminUpdateRequest adminUpdateRequest ){
        return true;
    }
    


}
