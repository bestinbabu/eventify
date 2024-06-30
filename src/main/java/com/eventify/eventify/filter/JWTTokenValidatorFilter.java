package com.eventify.eventify.filter;

import com.eventify.eventify.constants.SecurityConstants;
import com.eventify.eventify.exception.BadRequestException;
import com.eventify.eventify.exception.ForbiddenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String JwtToken = request.getHeader(SecurityConstants.JWT_HEADER);

        if (JwtToken != null) {
            try {
                SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(JwtToken)
                        .getPayload();

                String username = claims.get("username", String.class);
                List<String> authorities = claims.get("authorities", List.class);

                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", authorities)));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                throw new ForbiddenException("Invalid JWT Token");
            }
        }
        else {
                throw new BadRequestException("Missing JWT Token");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // This filter should not be applied to login requests and register requests
        return request.getServletPath().equals("/users/login") || request.getServletPath().equals("/users/register");
    }
}
