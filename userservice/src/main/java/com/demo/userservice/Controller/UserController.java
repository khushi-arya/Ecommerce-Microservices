package com.demo.userservice.Controller;


import com.demo.userservice.DTO.LoginRequest;
import com.demo.userservice.DTO.RegisterRequest;
import com.demo.userservice.Entity.User;
import com.demo.userservice.Repo.UserRepository;
import com.demo.userservice.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService authService;

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return  authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
