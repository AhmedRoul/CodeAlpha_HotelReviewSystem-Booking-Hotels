package com.HotelReviewSystem.Hotel.Review.System.Controllers;

import com.HotelReviewSystem.Hotel.Review.System.Config.Password.CustomPasswordEncoder;
import com.HotelReviewSystem.Hotel.Review.System.Model.Admin;
import com.HotelReviewSystem.Hotel.Review.System.Model.AdminRoles;
import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import com.HotelReviewSystem.Hotel.Review.System.Repository.AdminRepository;
import com.HotelReviewSystem.Hotel.Review.System.Repository.UserRepository;

import com.HotelReviewSystem.Hotel.Review.System.dto.Request.AdminRegisterRequest;
import com.HotelReviewSystem.Hotel.Review.System.dto.Request.AdminUpdateRequest;
import com.HotelReviewSystem.Hotel.Review.System.dto.Request.UpdateAdminRoleRequest;
import com.HotelReviewSystem.Hotel.Review.System.dto.Resopose.RegisterResponse;
import com.HotelReviewSystem.Hotel.Review.System.implement.InfoUser;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/V1/Admin")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@Slf4j
public class AdminController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    CustomPasswordEncoder passwordEncoder;
    @Autowired
    InfoUser  infoUser;

    @GetMapping
    @RolesAllowed("Admin")
    public ResponseEntity<?> getAdmin(@RequestHeader("Authorization") String authorizationHeader)
    {
       User user= infoUser.getAdmin(authorizationHeader);
       Admin admin =user.getAdmin();
       admin.getUser().setAdmin(null);
       return ResponseEntity.ok(admin);
    }
    @PostMapping
    @RolesAllowed("Admin_Master")
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
    @GetMapping("/All")
    @RolesAllowed("Admin_Master")
    public ResponseEntity<?> getAdmin(){

        return ResponseEntity.ok( infoUser.getAdmins());
    }

    @PutMapping
    @RolesAllowed({"Admin"})
    public ResponseEntity<?> UpdateAdmin(@RequestHeader("Authorization") String authorizationHeader,
                                       @RequestBody @Valid AdminUpdateRequest adminUpdateRequest){
        User user= infoUser.getAdmin(authorizationHeader);
        user= adminUpdateRequest.getChange(user);
        userRepository.save(user);
        return ResponseEntity.ok("Done Updated ");
    }

    @PutMapping("/Role")
    @RolesAllowed("Admin_Master")
    public ResponseEntity<?> UpdateAdminRole(@Valid @RequestBody UpdateAdminRoleRequest updateAdminRoleRequest){
         Optional<Admin> admin=adminRepository.findById(updateAdminRoleRequest.getId_admin());

        if(admin.isPresent()&&!admin.get().getRoles().contains(AdminRoles.Master)){
            Admin admin1=admin.get();
            admin1.setRoles(updateAdminRoleRequest.getRoles());
            adminRepository.save(admin1);
            return ResponseEntity.ok("Updated Admin Role");
        }
        else
        {
            return ResponseEntity.badRequest().body("Invalid Valid request  ");
        }
    }

    @DeleteMapping
    @RolesAllowed("Admin_Master")
    public ResponseEntity<?> DeleteAdmin(@RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(name = "Id") int idAdmin){
        User user= infoUser.getAdmin(authorizationHeader);

        Optional<Admin> admin=adminRepository.findById(idAdmin);

        if(user.getId()!=idAdmin&&admin.isPresent()&&!admin.get().getRoles().contains(AdminRoles.Master))
        {
            userRepository.deleteById((long) idAdmin);
            adminRepository.deleteById(idAdmin);
            return ResponseEntity.ok("Done Deleted");
        }

        else {
            return ResponseEntity.badRequest().body("Invalid Valid request shouldn't delete Admin has  Master role.");
        }

    }

}
