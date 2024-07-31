package com.HotelReviewSystem.Hotel.Review.System.Repository;

import com.HotelReviewSystem.Hotel.Review.System.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
}
