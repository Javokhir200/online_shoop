package uz.lee.onlineshoop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.entity.UserEntity;
import uz.lee.onlineshoop.repository.UserRepository;
import uz.lee.onlineshoop.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user)  {
        UserEntity entity = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
            UserEntity entity = userService.authenticate(username, password);
            if (entity != null) {
                return ResponseEntity.ok("Login successful");
            }
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid full name or password");
    }
}
