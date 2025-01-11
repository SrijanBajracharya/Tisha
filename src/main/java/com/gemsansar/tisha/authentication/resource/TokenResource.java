package com.gemsansar.tisha.authentication.resource;

import com.gemsansar.tisha.authentication.domain.dto.request.RefreshTokenRequest;
import com.gemsansar.tisha.authentication.domain.dto.response.AccessTokenResponse;
import com.gemsansar.tisha.platform.enums.AppRole;
import com.gemsansar.tisha.user.domain.User;
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
            AppRole role = AppRole.valueOf(claims.get("role", String.class));
            String firstName = claims.get("firstName", String.class);
            String lastName = claims.get("lastName", String.class);
            Long companyId = claims.get("companyId", Long.class);
            String email = claims.getSubject();

            User user = User.builder()
                    .id(userId)
                    .email(email)
                    .role(role)
                    .firstName(firstName)
                    .lastName(lastName)
                    .companyId(companyId)
                    .build();

            String newAccessToken = jwtUtil.generateAccessToken(user);

            return ResponseEntity.ok(new AccessTokenResponse(newAccessToken));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
    }
}
