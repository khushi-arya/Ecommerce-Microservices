package com.demo.userservice.Service;

import com.demo.userservice.DTO.LoginRequest;
import com.demo.userservice.DTO.RegisterRequest;
import com.demo.userservice.Entity.User;
import com.demo.userservice.Repo.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    // Register User
    public ResponseEntity<String> registerUser(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    // Login User
    public ResponseEntity<String> login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 🔐 Generate JWT Token here
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();

        return ResponseEntity.ok(token);


    }
}