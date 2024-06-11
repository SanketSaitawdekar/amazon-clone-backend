package com.sanket.amazonclone.service;

import com.sanket.amazonclone.model.User;
import com.sanket.amazonclone.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public User save(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
