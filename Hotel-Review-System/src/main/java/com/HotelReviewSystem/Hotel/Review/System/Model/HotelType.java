package com.HotelReviewSystem.Hotel.Review.System.Model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Hotel_Type")
public class HotelType {

    @Id
    private  Long IdHotel;


    @OneToOne
    @JoinColumn(name = "hotel_id")
    @MapsId
    private Hotel hotel;

    private Boolean breakfast;
    private Boolean cafe;
    private Boolean gym;
    private Boolean pool;
    private Boolean hotel_bar;
    private Boolean restaurant;
    private Boolean freeWifiInPublicAreas;
    private Boolean parking;
    private Boolean twentyFourHourReception;
    private Boolean twentyFourHourRoomService;
}
