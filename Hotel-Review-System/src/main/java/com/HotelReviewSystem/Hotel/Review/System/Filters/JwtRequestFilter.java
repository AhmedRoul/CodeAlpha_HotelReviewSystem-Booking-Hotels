package com.HotelReviewSystem.Hotel.Review.System.Filters;


import com.HotelReviewSystem.Hotel.Review.System.Util.CookiesUtil;
import com.HotelReviewSystem.Hotel.Review.System.Util.JwtUtil;
import com.HotelReviewSystem.Hotel.Review.System.implement.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    JwtUtil jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader=request.getHeader("Authorization");
        String email=null;

        String Token=null;
        if(request.getCookies()!=null)
        {
            Token= CookiesUtil.getToken(request.getCookies());
        }
        if(Token==null&&authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")&&!authorizationHeader.startsWith("Bearer Token")){
            Token=authorizationHeader.substring(7);
        }
        if(Token==null&&authorizationHeader!=null&&authorizationHeader.startsWith("Bearer Token ")){
            Token=authorizationHeader.substring(13);
        }
        if(Token==null&&authorizationHeader==null) {
            if (request.getRequestURI().equals("/api/V1/signup")||request.getRequestURI().equals("/api/V1/login")) {
                filterChain.doFilter(request, response);
                return;
            }
        }



        if (Token != null && !Token.isEmpty()) {
            try {
                email = jwtUtils.extractUsername(Token);
            } catch (Exception e) {
                System.out.println("Failed to extract username from token: " + e.getMessage());
            }

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                if (jwtUtils.validateToken(Token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
