package com.Lvoquang.sv7.services;

import com.Lvoquang.sv7.domain.Role;
import com.Lvoquang.sv7.dto.role.AddRoleRequest;
import com.Lvoquang.sv7.exceptions.InvalidRoleException;
import com.Lvoquang.sv7.exceptions.RoleExistedException;
import com.Lvoquang.sv7.repository.RoleRepository;
import com.Lvoquang.sv7.security.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity addRole(AddRoleRequest roleRequest) {

        Optional.ofNullable(getRoleFromRequest(roleRequest))
                .orElseThrow(() -> new InvalidRoleException());

        Role newRole = Role.builder()
                .name(getRoleFromRequest(roleRequest)).build();
        boolean isExistedRole = !roleRepository.findByName(newRole.getName()).equals(null);
        if (isExistedRole) throw new RoleExistedException();
        roleRepository.save(newRole);
        return ResponseEntity.ok().build();
    }

    private String getRoleFromRequest(AddRoleRequest role) {
        switch (role.getName()) {
            case "admin":
            case "role_admin":
                return Roles.ADMIN.getName();
            case "user":
            case "role_user":
                return Roles.USER.getName();
            default:
                return null;
        }
    }
}
