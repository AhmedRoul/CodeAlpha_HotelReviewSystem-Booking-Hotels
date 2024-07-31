package com.HotelReviewSystem.Hotel.Review.System.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Liked;
    private String Disliked;
    private Date date;
    private  float Rating;
    @OneToOne(mappedBy ="review")
    private Reservation reservation;

}
