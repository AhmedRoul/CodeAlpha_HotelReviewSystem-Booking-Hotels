package com.HotelReviewSystem.Hotel.Review.System.Util;


import com.HotelReviewSystem.Hotel.Review.System.Model.JWTBlackList;
import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import com.HotelReviewSystem.Hotel.Review.System.Repository.JWTBlackListRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil {
    @Autowired
    private JWTBlackListRepository repository;
    private final String SECRET_KEY = "6C44714E8B7966D8AD44AF7888B7A";
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration );
    }
    public  <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final  Claims claims=extarctAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extarctAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());

    }

    public String generateToken(User userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,userDetails.getEmail());
    }
    private String createToken(Map<String,Object>claims,String subject){
        return Jwts.builder()

                .setClaims(claims).
                setSubject(subject).
                setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username =extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token)
                && (repository.findByToken(token).isEmpty());
    }
    public Boolean SetBlockToken(String Token){
        final String username =extractUsername(Token);
        JWTBlackList jwtBlackList=JWTBlackList.builder().token(Token).email(username).build();
        log.info(jwtBlackList.toString());
         jwtBlackList=  repository.save(jwtBlackList);
         if(jwtBlackList!=null)
        return true;
         else
             return false;
    }
}
