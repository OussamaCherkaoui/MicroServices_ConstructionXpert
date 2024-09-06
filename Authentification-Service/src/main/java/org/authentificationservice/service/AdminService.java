package org.authentificationservice.service;

import lombok.RequiredArgsConstructor;
import org.authentificationservice.model.Admin;
import org.authentificationservice.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    public Admin save(Admin admin)
    {
        return adminRepository.save(admin);
    }
}
