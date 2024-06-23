package uz.lee.onlineshoop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.lee.onlineshoop.entity.UserEntity;
import uz.lee.onlineshoop.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user)  {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity loginRequest) {
            String fullName = loginRequest.getFull_name();
            String password = loginRequest.getPassword();
            UserEntity user = userService.authenticate(fullName, password);
            if (user != null) {
                return ResponseEntity.ok("Login successful");
            }
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid full name or password");
    }
}
