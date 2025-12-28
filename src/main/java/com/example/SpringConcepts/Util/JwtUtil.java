package com.example.SpringConcepts.Util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "qwertyuio asdfghjk xcvbnm, rtyuio bnm, rtyui cvbnm tyhjuk dfgbnm ertyui fv uhgvcnjkolkjn kjnb vhj" +
            "bnjkdfghj hn lmn";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    public String generateToken(String username){
        Date now = new Date();
        long expiryTime = 1000*60*60;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+expiryTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) throws ExpiredJwtException {
        Claims claims = getClaims(token);
        Date expiry = claims.getExpiration();
        try {
            return expiry.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public boolean isValidToken(String token) {
        return !isTokenExpired(token);
    }


    public String getSubject(String token){
        return getClaims(token).getSubject();
    }
}
