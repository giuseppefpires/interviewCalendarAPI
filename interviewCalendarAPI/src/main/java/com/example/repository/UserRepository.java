package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends ExtendedRepository<User, Long> {

}
