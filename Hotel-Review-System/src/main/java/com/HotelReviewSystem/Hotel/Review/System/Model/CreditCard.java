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
@Table(name = "CreditCard")
public class CreditCard {
     @Id
     private  String CardNumber;
     private  String NameOnCard;
     private  String expirationDate;
     private  String securityCode;
     private  String balanceEGP;

     private boolean ISAccept;
     @OneToMany(mappedBy = "creditCard")
     private List<Reservation> reservations;

}
