package com.HotelReviewSystem.Hotel.Review.System.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/api/login")
    public String logind() {
        return "login";
    }
}
