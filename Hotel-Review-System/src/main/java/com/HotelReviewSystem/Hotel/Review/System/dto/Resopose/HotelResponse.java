package com.HotelReviewSystem.Hotel.Review.System.dto.Resopose;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResponse {
    private String name;
    private String government;
    private String city;
    private String address;
    private String idpoint;
    private String urlLocation;

}
