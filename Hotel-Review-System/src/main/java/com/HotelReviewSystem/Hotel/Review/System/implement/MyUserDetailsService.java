package com.HotelReviewSystem.Hotel.Review.System.implement;


import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import com.HotelReviewSystem.Hotel.Review.System.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email)  {

        try{
            User user=userRepository.findByEmail(email);

            String role=null;
            if(user.getAdmin()!=null)
                role="Admin";
            else if(user.getCustomer()!=null){
                role="Customer";
            }else
                role="";

            List<GrantedAuthority> authorities;
            if(user.getAdmin() != null) {
                authorities = buildUserAuthority(user.getAdmin().getRoles().toString());
            } else if(user.getCustomer() != null) {
                authorities = buildUserAuthority("Customer"); // or any other role you want to assign to customers
            } else {

                throw new UsernameNotFoundException("User not found");
            }

            return buildUserForAuthentication(user, authorities);
        }
        catch (UsernameNotFoundException e){

            log.error("MyUserDetailsService  : throw" +e.toString());

            throw  e;
        }


    }
    private List<GrantedAuthority> buildUserAuthority(List<String> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();


        setAuths.add(new SimpleGrantedAuthority("Admin"));
        for (String userRole : userRoles)
        {
            setAuths.add(new SimpleGrantedAuthority("Admin_" + userRole));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(setAuths);
        return grantedAuthorities;
    }
    private List<GrantedAuthority> buildUserAuthority(String userRole) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority(userRole.toString()));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(setAuths);
        return grantedAuthorities;
    }
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        //accountNonExpired, credentialsNonExpired, accountNonLocked, authorities properties
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}

