package com.HotelReviewSystem.Hotel.Review.System.Repository;

import com.HotelReviewSystem.Hotel.Review.System.Model.JWTBlackList;
import com.HotelReviewSystem.Hotel.Review.System.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component

public class TokenScheduler {
    @Autowired
    private JWTBlackListRepository repository;
    @Autowired
    private JwtUtil jwtUtil;

    @Scheduled(fixedRate = 3600000) // 3600000 milliseconds = 1 hour
    public void deleteExpiredTokens() {
        List<JWTBlackList> jwtBlackLists = repository.findAll();
        jwtBlackLists.forEach(jwtBlackList -> {
            Date expirationDate = jwtUtil.extractExpiration(jwtBlackList.getToken());
            Date now = new Date();
            long threshold = 30000; // 30 seconds
            if (expirationDate.before(new Date(now.getTime() - threshold))) {
                repository.delete(jwtBlackList);
            }
        });
    }
}
