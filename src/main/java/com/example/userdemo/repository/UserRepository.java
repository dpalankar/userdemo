package com.example.userdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userdemo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
