package org.authentificationservice.service;


import lombok.RequiredArgsConstructor;
import org.authentificationservice.model.User;
import org.authentificationservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public User save(User user)
    {
        return userRepository.save(user);
    }


    public User getById(Long idUser) {
        Optional<User> user = userRepository.findById(idUser);
        return user.get();
    }
}
