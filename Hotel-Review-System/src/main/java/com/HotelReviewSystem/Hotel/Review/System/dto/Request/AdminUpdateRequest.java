package com.HotelReviewSystem.Hotel.Review.System.dto.Request;

import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateRequest {
    @Email(message = "INVALID EMAIL ADDRESS")
    @NotBlank(message = "EMAIL CANNOT BE EMPTY")
    @Pattern(regexp = "[^@]+@admin\\..+", message = "EMAIL MUST INCLUDE 'admin' AFTER '@'")
    private String email;

    @Size(min = 3,max = 20,message = "First name must be at least 8 characters long, at most 20 characters")
    private String firstname;


    @Size(min = 3,max = 20,message = "Last name must be at least 8 characters long, at most 20 characters")
    private String lastname;

    public User getChange(User user){
        if(!Objects.equals(user.getFirstname(), firstname))
            user.setFirstname(firstname);
        if(!Objects.equals(user.getLastname(), lastname))
            user.setLastname(lastname);
        if(!Objects.equals(user.getEmail(), email))
            user.setEmail(email);
        return user;
    }
}
