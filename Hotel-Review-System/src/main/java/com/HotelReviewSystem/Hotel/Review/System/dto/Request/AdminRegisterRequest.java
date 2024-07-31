package com.HotelReviewSystem.Hotel.Review.System.dto.Request;

import com.HotelReviewSystem.Hotel.Review.System.Model.Admin;
import com.HotelReviewSystem.Hotel.Review.System.Model.AdminRoles;
import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegisterRequest {
    @Email(message = "INVALID EMAIL ADDRESS")
    @NotBlank(message = "EMAIL CANNOT BE EMPTY")
    @Pattern(regexp = "[^@]+@admin\\..+", message = "EMAIL MUST INCLUDE 'admin' AFTER '@'")
    private String email;

    @NotBlank(message = "Password CANNOT BE EMPTY")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,20}$"
            ,message = "Password must be at least 8 characters long , at most 20 characters,contain at least one digit, one lowercase letter, one uppercase letter, and one letter.")
    private String password;


    @Size(min = 3,max = 20,message = "First name must be at least 8 characters long, at most 20 characters")
    private String firstname;


    @Size(min = 3,max = 20,message = "Last name must be at least 8 characters long, at most 20 characters")
    private String lastname;

    @NotEmpty(message = "ROLES CANNOT BE EMPTY")

    private List<AdminRoles> roles;
    public User GetUser(){
        return User.builder().firstname(this.firstname).lastname(this.lastname).email(this.email).password(this.password).role("Admin").build();
    }
    public Admin GetAdmin(User user){
        return Admin.builder().roles(roles).user(user).build();
    }
}
