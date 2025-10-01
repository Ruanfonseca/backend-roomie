package com.servicepro.alpha.controller.auth;

import com.servicepro.alpha.dto.auth.LoginRequest;
import com.servicepro.alpha.dto.auth.LoginResponse;
import com.servicepro.alpha.dto.auth.UserInfo;
import com.servicepro.alpha.secure.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    public AuthController(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // MOCK: valida usuário fixo
        if ("admin@teste.com".equals(loginRequest.getEmail()) && "123456".equals(loginRequest.getPassword())) {
            String token = tokenProvider.generateToken(loginRequest.getEmail(), "ADMIN");

            UserInfo user = new UserInfo("1", loginRequest.getEmail(), "Administrador", "ADMIN");

            return ResponseEntity.ok(new LoginResponse(token, user));
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (tokenProvider.validateToken(token)) {
            return ResponseEntity.ok("Token válido");
        }
        return ResponseEntity.status(401).body("Token inválido");
    }
}