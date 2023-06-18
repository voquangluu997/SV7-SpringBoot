package com.Lvoquang.sv7.repository;

import com.Lvoquang.sv7.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(long id);
    User findByEmail(String email);



}
