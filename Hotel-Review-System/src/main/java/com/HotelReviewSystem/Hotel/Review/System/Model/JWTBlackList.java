package com.HotelReviewSystem.Hotel.Review.System.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="jwtblacklist")
public class JWTBlackList {

    @Id
    private String token;
    private String email;

    @CreationTimestamp
    private Timestamp timestamp;

    @Override
    public String toString() {
        return "JWTBlackList{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}