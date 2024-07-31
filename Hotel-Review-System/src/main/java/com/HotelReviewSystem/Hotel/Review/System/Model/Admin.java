package com.HotelReviewSystem.Hotel.Review.System.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Admin")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Admin {
    @Id
    private Long id;


    @Enumerated(EnumType.STRING)
    private List<AdminRoles> roles;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;


}
