package com.article.api.repository;

import com.article.api.beans.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByUserName(String username);
    Users findByEmail(String email);
}
