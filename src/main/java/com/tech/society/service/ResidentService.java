package com.tech.society.service;

import com.tech.society.dto.UserDto;

import java.util.List;

public interface ResidentService {
    void registerUser(UserDto userDto);
    List<UserDto> getAllUsers();
}
