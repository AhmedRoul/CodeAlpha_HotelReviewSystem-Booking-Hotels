package com.HotelReviewSystem.Hotel.Review.System.Repository;

import com.HotelReviewSystem.Hotel.Review.System.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer,Integer> {
}
