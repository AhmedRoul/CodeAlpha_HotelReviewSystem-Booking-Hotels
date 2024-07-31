package com.HotelReviewSystem.Hotel.Review.System.Repository;


import com.HotelReviewSystem.Hotel.Review.System.Model.JWTBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JWTBlackListRepository extends JpaRepository<JWTBlackList,String> {
    Optional<JWTBlackList> findByToken(String token);
    boolean existsByToken(String token);
}
