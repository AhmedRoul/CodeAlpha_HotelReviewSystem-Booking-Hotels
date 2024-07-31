package com.HotelReviewSystem.Hotel.Review.System.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Room_Type")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long ID;

    private String name;
    private String description;
    private int max_guest;
    private int type_id;
    private boolean openableWindows;
    private boolean shower;
    private boolean television;
    private boolean wifi;
    private boolean airConditioning;
    private boolean desk;
    private boolean closet;
    private boolean telephone;
    private boolean centralHeating;
    private boolean electricKettle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    private List<Room> room;
}
