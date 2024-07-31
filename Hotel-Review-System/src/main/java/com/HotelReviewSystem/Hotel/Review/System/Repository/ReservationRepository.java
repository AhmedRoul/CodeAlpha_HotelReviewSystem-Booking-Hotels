package com.HotelReviewSystem.Hotel.Review.System.Repository;

import com.HotelReviewSystem.Hotel.Review.System.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
}
