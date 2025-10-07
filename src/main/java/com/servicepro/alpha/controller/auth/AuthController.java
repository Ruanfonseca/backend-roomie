package com.servicepro.alpha.controller.auth;

import com.servicepro.alpha.domain.Usuario;
import com.servicepro.alpha.dto.auth.LoginRequest;
import com.servicepro.alpha.dto.auth.LoginResponse;
import com.servicepro.alpha.dto.auth.UserInfo;
import com.servicepro.alpha.repository.UsuarioRepository;
import com.servicepro.alpha.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
                          UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.usuarioRepository = usuarioRepository;

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try {
            System.out.println("🔐 Recebendo login do usuário: " + loginRequest.getEmail());

            // Autentica usando Spring Security
            System.out.println("🔄 Tentando autenticar usuário com AuthenticationManager...");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            System.out.println("✅ Autenticação bem-sucedida!");

            // Recupera os dados do usuário do banco de dados
            Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado no banco"));
            System.out.println("📄 Usuário encontrado no banco: " + usuario.getEmail());
            System.out.println("📝 Nome: " + usuario.getName() + ", Role: " + usuario.getRole());

            // Gera o token JWT usando email e role
            String role = usuario.getRole().name();
            String token = tokenProvider.generateToken(usuario.getEmail(), role);
            System.out.println("🔑 Token JWT gerado: " + token);

            // Cria DTO de resposta com os dados reais do usuário
            UserInfo userInfo = new UserInfo(
                    String.valueOf(usuario.getId()),
                    usuario.getEmail(),
                    usuario.getName(),
                    role
            );
            System.out.println("📤 Preparando resposta de login com usuário e token...");

            return ResponseEntity.ok(new LoginResponse(token, userInfo));

        } catch (BadCredentialsException e) {
            e.printStackTrace();
            System.out.println("❌ Credenciais inválidas para: " + loginRequest.getEmail());
            return ResponseEntity.status(401).body("Credenciais inválidas");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⚠️ Erro ao autenticar usuário: " + loginRequest.getEmail());
            return ResponseEntity.status(500).body("Erro ao autenticar");
        }
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
