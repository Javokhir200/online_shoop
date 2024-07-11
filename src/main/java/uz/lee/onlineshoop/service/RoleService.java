package uz.lee.onlineshoop.service;


import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.RoleDto;
import uz.lee.onlineshoop.entity.Comment;
import uz.lee.onlineshoop.entity.RoleEntity;
import uz.lee.onlineshoop.repository.RoleEntityRepository;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleEntityRepository repository;

    public RoleService(RoleEntityRepository repository) {
        this.repository = repository;
    }

    public ApiResponse getAll() {
        return new ApiResponse(true, "roles", repository.findAll());
    }

    public ApiResponse addRole(RoleDto roleDto) {
        RoleEntity role = new RoleEntity();
        role.setDescription(roleDto.getDescription());
        role.setName(roleDto.getName());

        repository.save(role);

        return new ApiResponse(true, "Role added successfully!!!");
    }

    public ApiResponse updateRole(Integer id, RoleDto roleDto) {
        Optional<RoleEntity> optionalRole = repository.findById(id);
        if (!optionalRole.isPresent()) {
            return new ApiResponse(false, "Role not found");
        }
        RoleEntity existingRole = optionalRole.get();

        existingRole.setDescription(roleDto.getDescription());
        existingRole.setName(roleDto.getName());
        repository.save(existingRole);
        return new ApiResponse(true, "Role updated successfully");
    }

    public ApiResponse deleteRole(Integer id) {
        Optional<RoleEntity> optionalRole = repository.findById(id);
        if (!optionalRole.isPresent()) {
            return new ApiResponse(false, "Role not found");
        }
        RoleEntity role = optionalRole.get();
        repository.delete(role);
        return new ApiResponse(true, "Role deleted successfully");
    }
}
