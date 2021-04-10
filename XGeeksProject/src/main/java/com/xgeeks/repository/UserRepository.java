package com.xgeeks.repository;

import org.springframework.stereotype.Repository;

import com.xgeeks.model.User;

@Repository
public interface UserRepository extends ExtendedRepository<User, Long> {

}
