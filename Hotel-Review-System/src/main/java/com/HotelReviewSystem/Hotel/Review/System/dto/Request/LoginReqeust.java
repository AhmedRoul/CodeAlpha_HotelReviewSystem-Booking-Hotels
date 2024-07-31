package com.HotelReviewSystem.Hotel.Review.System.dto.Request;
import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginReqeust {
    @Email(message = "INVALID_EMAIL_ADDRESS")
    @NotBlank(message = "EMAIL_CANNOT_BE_EMPTY")
    private String email;

    @NotBlank(message = "Password_CANNOT_BE_EMPTY")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,100}$"
            ,message = "INVALID_EMAIL_ADDRESS")
    private String password;
    public User GetUser(){
        return User.builder().email(this.email).build();
    }
}
