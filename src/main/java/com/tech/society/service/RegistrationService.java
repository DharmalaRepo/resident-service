package com.tech.society.service;

import com.tech.society.dto.UserDto;
import com.tech.society.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    @Autowired
    private EmailServiceImpl emailService;

    public void onUserAdded(UserDto user) {
        String userEmail = user.getEmail();
        String subject = "New user added";
        String body = "A new user has been added: " + user.getName();
        emailService.sendEmail(userEmail, subject, body);
    }
}
