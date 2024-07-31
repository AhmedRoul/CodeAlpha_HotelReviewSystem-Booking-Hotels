package com.HotelReviewSystem.Hotel.Review.System.dto.Resopose;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponse {
    private boolean isTokenExpired;
    private  String role;
    private  String email;
    private  String token;



}
