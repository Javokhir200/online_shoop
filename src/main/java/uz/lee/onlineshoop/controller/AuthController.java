package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.auth.LoginDto;
import uz.lee.onlineshoop.dto.auth.RegisterDto;
import uz.lee.onlineshoop.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        ApiResponse resp = authService.register(registerDto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @GetMapping("/verify/{verificationCode}")
    public ResponseEntity<?> verifyEmail(@PathVariable String verificationCode){
        ApiResponse resp = authService.verifyEmail(verificationCode);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        ApiResponse resp = authService.login(loginDto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }
}
