package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.RoleDto;
import uz.lee.onlineshoop.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse resp = roleService.getAll();
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto){
        ApiResponse resp = roleService.addRole(roleDto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }
}
