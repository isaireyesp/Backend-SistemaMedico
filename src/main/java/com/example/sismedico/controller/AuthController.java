package com.example.sismedico.controller;

import com.example.sismedico.dto.request.LoginRequest;
import com.example.sismedico.dto.request.RegisterRequest;
import com.example.sismedico.dto.response.AuthResponse;
import com.example.sismedico.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    /**
     * Registrar usuario
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        AuthResponse response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Iniciar sesión
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Obtener perfil del usuario autenticado
     */
    @GetMapping("/me")
    public ResponseEntity<?> me() {
        return ResponseEntity.ok(authService.me());
    }

    /**
     * Cerrar sesión
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        authService.logout();

        return ResponseEntity.ok("Sesión cerrada correctamente.");
    }

}