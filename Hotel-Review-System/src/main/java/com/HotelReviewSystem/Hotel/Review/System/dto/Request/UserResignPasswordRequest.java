package com.HotelReviewSystem.Hotel.Review.System.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResignPasswordRequest {
    @NotBlank(message = "Password CANNOT BE EMPTY")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,20}$"
            ,message = "Password must be at least 8 characters long , at most 20 characters,contain at least one digit, one lowercase letter, one uppercase letter, and one letter.")
    private String password;

    @NotBlank(message = "Password CANNOT BE EMPTY")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,20}$"
            ,message = "Password must be at least 8 characters long , at most 20 characters,contain at least one digit, one lowercase letter, one uppercase letter, and one letter.")
    private String NewPassword;
}
