package com.HotelReviewSystem.Hotel.Review.System.dto.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequest {
    private String accessToken;
}
