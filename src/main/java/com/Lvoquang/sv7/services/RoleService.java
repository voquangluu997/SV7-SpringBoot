package com.Lvoquang.sv7.services;

import com.Lvoquang.sv7.dto.role.AddRoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public interface RoleService {

    @PostMapping()
    public ResponseEntity<Object> addRole(@RequestBody AddRoleRequest roleRequest);
}
