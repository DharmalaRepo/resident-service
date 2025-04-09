package com.tech.society.service.impl;

import com.tech.society.dto.UserDto;
import com.tech.society.entity.User;
import com.tech.society.repository.UserRepository;
import com.tech.society.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setRole(userDto.getRole());
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setRole(user.getRole());
            return dto;
        }).collect(Collectors.toList());
    }
}
