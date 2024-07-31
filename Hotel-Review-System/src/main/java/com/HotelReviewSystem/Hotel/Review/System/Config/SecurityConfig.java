package com.HotelReviewSystem.Hotel.Review.System.Config;


import com.HotelReviewSystem.Hotel.Review.System.Config.Password.CustomPasswordEncoder;
import com.HotelReviewSystem.Hotel.Review.System.Filters.JwtRequestFilter;
import com.HotelReviewSystem.Hotel.Review.System.implement.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
/*@EnableGlobalMethodSecurity(prePostEnabled = true)*/

public class SecurityConfig {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;


    // ...


    private  static  final  String[] WHITELIST={
            "/resources/**", "/static/**", "/static.css/**", "/js/**", "/static.images/**",
            "/api/V1/signup","/api/V1/login","/api/V1/tokenCheck","/api/V1/add"

    };
   /* private static  final  String[] WHITELISTADMIN={"/api/V1/user"};
    private static  final  String[] WHITELISTUSER={"/api/V1/user"};*/

    private static final String[]  ADMINACCESS={

    };
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())

                .authorizeRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers("/api/V1/signup").permitAll()
                                .requestMatchers("/api/V1/login").permitAll()
                                .requestMatchers(WHITELIST).permitAll()
                                
                       .anyRequest().authenticated()

                )

                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                ;


        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.debug(true).ignoring().requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(customPasswordEncoder);
        return authenticationProvider;

    }


    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }



}