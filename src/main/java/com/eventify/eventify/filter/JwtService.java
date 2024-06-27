package com.eventify.eventify.filter;

import com.eventify.eventify.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static com.eventify.eventify.constants.SecurityConstants.JWT_KEY;

@Service
public class JwtService {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }




    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }




    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        Object authorities = populateAuthorities(authentication.getAuthorities());
        return buildToken(email, authorities);
    }

    private String buildToken(String email, Object authorities) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new java.util.Date())
                .expiration(new java.util.Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .claim("authorities", authorities)
                .signWith(getSigningKey())
                .compact();
}
    private Object populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritySet = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            authoritySet.add(authority.getAuthority());
        }
        return String.join(",", authoritySet);
    }

    private SecretKey getSigningKey() {
        byte [] keyBytes = Decoders.BASE64.decode(JWT_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }




    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new java.util.Date());
    }

    public boolean isTokenValid(String token, String email) {
        final String emailFromToken = extractEmail(token);
        return email.equals(emailFromToken) && !isTokenExpired(token);
    }

}
