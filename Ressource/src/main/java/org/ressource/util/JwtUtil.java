package org.ressource.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;

@Component
public class JwtUtil {
    private static final String SECRET = "671491AE98362741F722202EED3288E8FF2508B35315ADBF75EEB3195A926B40"; // Base64 encoded secret

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT token");
        }

        String username = claims.getSubject();
        String role = claims.get("role", String.class);

        System.out.println("Username: " + username);
        System.out.println("Role: " + role);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

        System.out.println("Authority: " + authority);

        UserDetails userDetails = new User(
                username,
                "",  // Password is empty
                Collections.singleton(authority)
        );

        return new UsernamePasswordAuthenticationToken(userDetails, token, Collections.singleton(authority));
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
