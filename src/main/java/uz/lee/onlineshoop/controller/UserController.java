package uz.lee.onlineshoop.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.user.EditByUserDto;
import uz.lee.onlineshoop.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> editUser(@RequestBody EditByUserDto userDto, @PathVariable Long userId){
         ApiResponse resp = userService.editByUser(userId,userDto);
         return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }


    //getByID
}