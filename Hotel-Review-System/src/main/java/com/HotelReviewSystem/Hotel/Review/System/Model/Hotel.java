package com.HotelReviewSystem.Hotel.Review.System.Model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;
    private String Name;
    private  String id_point;
    private String city;
    private  String Government;
    private String Address;
    private String urlLocation;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();
    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
    private HotelType hotelType;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<RoomType> roomTypes = new ArrayList<>();



}
