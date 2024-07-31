package com.HotelReviewSystem.Hotel.Review.System.dto.Resopose;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterResponse {
    private String email;
    private String password;
    private String firstname;
    private String lastname;

    private String address;

    private String phoneNumber;
    private String birthday;

    private String nationality;

}
