package com.article.api.service;

import com.article.api.beans.Users;
import com.article.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserDetails {
    @Autowired
    UserRepository userDetails;

    public Optional<Users> findById(Integer UserId) {
        Optional<Users> Users = userDetails.findById(UserId);
        return Users;
    }

    public Optional<Users> saveUser(Users users){
        Users savedUser = userDetails.save(users);
        return this.findById(savedUser.getId());
    }
}
