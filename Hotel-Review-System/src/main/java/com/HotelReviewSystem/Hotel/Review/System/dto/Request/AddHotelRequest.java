package com.HotelReviewSystem.Hotel.Review.System.dto.Request;

import com.HotelReviewSystem.Hotel.Review.System.Model.Hotel;
import com.HotelReviewSystem.Hotel.Review.System.Model.HotelType;
import com.HotelReviewSystem.Hotel.Review.System.Model.Room;
import com.HotelReviewSystem.Hotel.Review.System.Model.RoomType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddHotelRequest {
    @Size(min = 3,max = 20,message = " name Hotel must be at least 8 characters long, at most 20 characters")
    private String name;


    @Size(min = 3,max = 20,message = "government must be at least 3 characters long, at most 20 characters")
    private String government;
    @Size(min = 3,max = 20,message = "city must be at least 3 characters long, at most 20 characters")
    private String city;
    @Size(min = 20,max = 500,message = "address must be at least 20 characters long, at most 500 characters")
    private String address;
    @Size(min = 3,max = 100,message = "idpoint must be at least 3 characters long, at most 100 characters")
    private String idpoint;
    @Size(min = 3,max = 500,message = "urlLocation must be at least 3 characters long, at most 500 characters")
    private String urlLocation;
    @NotNull(message = "hotelType is required")

    private HotelType hotelType;
    /*@NotEmpty(message = "roomTypes is required and cannot be empty")

    private  List<RoomType> roomTypes;*/

    public Hotel getHotel()
    {
        return Hotel.builder()
                .Name(name)
                .Address(address)
                .city(city)
                .Government(government)
                .urlLocation(urlLocation)
                .id_point(idpoint)
                //.roomTypes(roomTypes)
                .hotelType(hotelType)
                .build();
    }

}
