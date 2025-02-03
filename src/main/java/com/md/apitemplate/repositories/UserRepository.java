package com.md.apitemplate.repositories;

import com.md.apitemplate.entities.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.jpa.repository.JpaRepository;

@EnableScan
public interface UserRepository extends JpaRepository<User, String> {
}
