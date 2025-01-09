package com.gemsansar.tisha.authentication.resource;

import com.gemsansar.tisha.authentication.domain.dto.request.RefreshTokenRequest;
import com.gemsansar.tisha.authentication.domain.dto.response.AccessTokenResponse;
import com.gemsansar.tisha.user.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class TokenResource {

    private final JwtUtil jwtUtil;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAccessToken(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        if (jwtUtil.validateToken(refreshToken)) {
            Claims claims = jwtUtil.extractClaims(refreshToken);
            Long userId = claims.get("userId", Long.class);
            String username = claims.getSubject();

            String newAccessToken = jwtUtil.generateAccessToken(userId, username);

            return ResponseEntity.ok(new AccessTokenResponse(newAccessToken));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
    }
}
