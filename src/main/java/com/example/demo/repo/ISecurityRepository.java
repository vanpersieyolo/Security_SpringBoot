package com.example.demo.repo;

import com.example.demo.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface ISecurityRepository extends CrudRepository<AppUser,Long> {
    AppUser findByUserName(String username);
}
