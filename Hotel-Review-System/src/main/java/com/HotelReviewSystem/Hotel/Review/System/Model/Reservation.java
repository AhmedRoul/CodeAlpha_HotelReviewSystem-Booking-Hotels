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
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date open;
    private Date BeginningPeriodOfStay;
    private Date EndingPeriodOfStay;
    private float TotalPrice;
    private Boolean CheckerCreditCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creditcard_cardnumber")
    private CreditCard creditCard;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;



}
