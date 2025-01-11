package com.gemsansar.tisha.platform.config;

import com.gemsansar.tisha.platform.enums.AppRole;
import com.gemsansar.tisha.user.domain.User;
import com.gemsansar.tisha.user.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                Claims claims = jwtUtil.extractClaims(token);
                String email = claims.getSubject();
                Long userId = claims.get("userId", Long.class);
                AppRole role = AppRole.valueOf(claims.get("role", String.class));
                String firstName = claims.get("firstName", String.class);
                String lastName = claims.get("lastName", String.class);
                Long companyId = claims.get("companyId", Long.class);

                // Create a UserEntity or similar custom user object
                User customUser = User.builder()
                        .id(userId)
                        .email(email)
                        .role(role)
                        .firstName(firstName)
                        .lastName(lastName)
                        .companyId(companyId)
                        .build();

                // Set authentication in SecurityContext
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(customUser, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("SecurityContext updated with user: " + customUser);
            } else {
                System.out.println("Invalid token");
            }
        }

        filterChain.doFilter(request, response);
    }
}
