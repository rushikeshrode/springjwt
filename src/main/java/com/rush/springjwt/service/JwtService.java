package com.rush.springjwt.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void initKeys(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    Date issuedAt = new Date(System.currentTimeMillis());
    Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 1 hour

    // 1. Generate Token
    public String generateToken(String username){

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    // 2. Extract username from token
    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    // 3. checks expiry of token
    public boolean isTokenExpired(String token){
        Date expiryDate = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration();
        return expiryDate.before(new Date());
    }

    // 4.check if token valid (still not expired)
    public boolean validateToken(String token, String username){
        String extractUsername = extractUsername(username);
        return extractUsername.equals(username) && !isTokenExpired(token);
    }

}
