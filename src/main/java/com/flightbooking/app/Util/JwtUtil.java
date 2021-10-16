package com.flightbooking.app.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    private String SECRET_KEY = "secretDemo";
    public static final long TIMEOUT_SESSION=1000 * 60 * 60;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }
    private Claims extractAllClaims(String token) {
    	//get claims from the token using parser with same secret key
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    //GENERATE TOKEN BASED ON THE DUMMY SECRET KEY
    private String createToken(Map<String, Object> claims, String subject) {
    	//subject is the username
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TIMEOUT_SESSION)) //1minute
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    // FOR EVERY OPERATION WE RELY ON USER DETAILS
    public Boolean validateToken(String token, UserDetails userDetails) {
    	//VALIDATE IF TOKEN EXPIRED & TOKEN HAS SAME USERNAME AS PRINCIPAL OBJECT
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
