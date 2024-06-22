package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.entity.UserEntity;
import uz.lee.onlineshoop.repository.UserRepository;
import uz.lee.onlineshoop.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        userService.authenticate(user.getFull_name(), user.getPassword());
        return ResponseEntity.ok(userRepository.save(user));
    }
    @PostMapping()
    public ResponseEntity<?> login(@RequestParam String username, String fullName) {
        UserEntity user = userService.authenticate(username, fullName);
        if(user != null) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.status(401).body("Invalid username or password!");
    }
}
