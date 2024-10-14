package com.example.www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.www.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	public Optional<Users> findByUsername(String username);
}
