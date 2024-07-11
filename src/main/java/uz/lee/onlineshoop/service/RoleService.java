package uz.lee.onlineshoop.service;


import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.RoleDto;
import uz.lee.onlineshoop.entity.RoleEntity;
import uz.lee.onlineshoop.repository.RoleEntityRepository;

import javax.management.relation.Role;

@Service
public class RoleService {

    private final RoleEntityRepository repository;

    public RoleService(RoleEntityRepository repository) {
        this.repository = repository;
    }

    public ApiResponse getAll() {
        return new ApiResponse(true,"roles",repository.findAll());
    }

    public ApiResponse addRole(RoleDto roleDto) {
        RoleEntity role = new RoleEntity();
        role.setDescription(roleDto.getDescription());
        role.setName(roleDto.getName());

        repository.save(role);

        return new ApiResponse(true,"Role added successfully!!!");
    }
}
