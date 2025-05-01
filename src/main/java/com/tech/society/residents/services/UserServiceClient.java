package com.tech.society.residents.services;


import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.models.User;
import com.tech.society.residents.util.AppLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class UserServiceClient {

    @Value("${user-service.url}")
    private String userServiceUrl;

    @Value("${user-service.auth.username}")
    private String authUsername;

    @Value("${user-service.auth.password}")
    private String authPassword;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean registerUser(User user, RequestContext ctx) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // ✅ Set Basic Auth header
            String auth = authUsername + ":" + authPassword;
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeader = "Basic " + new String(encodedAuth);
            headers.set("Authorization", authHeader);

            HttpEntity<User> request = new HttpEntity<>(user, headers);

            ResponseEntity<User> response = restTemplate.exchange(
                    userServiceUrl,
                    HttpMethod.POST,
                    request,
                    User.class
            );

            AppLogger.log("registerUser", ctx.getUserName(), "Registered user via user-service");
            return true;
        } catch (Exception e) {
            AppLogger.logError("registerUser", ctx.getUserName(), "Failed to call user-service", e);
            return false;
        }
    }
}