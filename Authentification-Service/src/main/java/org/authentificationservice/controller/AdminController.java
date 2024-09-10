package org.authentificationservice.controller;

import lombok.RequiredArgsConstructor;
import org.authentificationservice.enums.Role;
import org.authentificationservice.model.Admin;
import org.authentificationservice.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AdminController {
    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerUser(@RequestBody Admin admin) {
        admin.setRole(Role.ADMIN);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return new ResponseEntity<>(adminService.save(admin), HttpStatus.CREATED);
    }
}
