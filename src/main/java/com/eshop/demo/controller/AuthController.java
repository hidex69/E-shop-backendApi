package com.eshop.demo.controller;

import com.eshop.demo.config.jwt.JwtProvider;
import com.eshop.demo.models.AuthRequest;
import com.eshop.demo.models.AuthResponse;
import com.eshop.demo.models.RegistrationRequest;
import com.eshop.demo.models.UserEntity;
import com.eshop.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest registrationRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(registrationRequest.getLogin());
        userEntity.setPassword(registrationRequest.getPassword());
        userService.saveUser(userEntity);
        return "register norm";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
}
