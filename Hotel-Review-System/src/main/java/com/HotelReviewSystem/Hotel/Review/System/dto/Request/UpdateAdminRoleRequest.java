package com.HotelReviewSystem.Hotel.Review.System.dto.Request;

import com.HotelReviewSystem.Hotel.Review.System.Model.AdminRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdminRoleRequest {
    private  int id_admin;
    private List<AdminRoles> roles;
}
