package com.HotelReviewSystem.Hotel.Review.System.Repository;


import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long > {
    public User findByEmail(String email);
    public boolean existsByEmail(String email);
}
