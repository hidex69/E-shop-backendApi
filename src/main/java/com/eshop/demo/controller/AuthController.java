package com.eshop.demo.controller;

import com.eshop.demo.config.CustomUserDetails;
import com.eshop.demo.config.jwt.JwtProvider;
import com.eshop.demo.models.auth.AuthRequest;
import com.eshop.demo.models.auth.AuthResponse;
import com.eshop.demo.models.auth.RegistrationRequest;
import com.eshop.demo.models.user.UserDto;
import com.eshop.demo.models.user.UserEntity;
import com.eshop.demo.repository.UserEntityRepository;
import com.eshop.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserEntityRepository userEntityRepository;

    private AuthenticationManager authenticationManager;

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
        String token = jwtProvider.generateToken(CustomUserDetails.fromUserEntityToCustumUserDetails(userEntity));
        return new AuthResponse(token);
    }

    @GetMapping("/check")
    public UserDto check(@RequestParam String token) {
        UserDto user = new UserDto();
        UserEntity userEntity = userEntityRepository.findByLogin(jwtProvider.getLoginFromToken(token));

        if (!jwtProvider.validateToken(token)) {
            user.setValid(false);
        } else {
            user.setValid(true);
            user.setLogin(userEntity.getLogin());
            user.setRoleEntity(userEntity.getRoleEntity());
        }

        return user;
    }
}
