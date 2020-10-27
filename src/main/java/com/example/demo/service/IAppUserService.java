package com.example.demo.service;

import com.example.demo.model.AppUser;

public interface IAppUserService {
    AppUser getUserByUsername(String username);
}
