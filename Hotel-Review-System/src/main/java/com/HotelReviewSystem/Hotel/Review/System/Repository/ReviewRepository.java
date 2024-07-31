package com.HotelReviewSystem.Hotel.Review.System.Repository;

import com.HotelReviewSystem.Hotel.Review.System.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
