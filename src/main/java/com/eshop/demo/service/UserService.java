package com.eshop.demo.service;

import com.eshop.demo.config.jwt.JwtFilter;
import com.eshop.demo.config.jwt.JwtProvider;
import com.eshop.demo.exceptions.NoTokenException;
import com.eshop.demo.models.user.RoleEntity;
import com.eshop.demo.models.user.UserEntity;
import com.eshop.demo.repository.RoleEntityRepository;
import com.eshop.demo.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private JwtProvider jwtProvider;

    public UserEntity saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }

    public UserEntity findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public UserEntity getUserByRequest(HttpServletRequest request) throws NoTokenException {
        String token = jwtFilter.getTokenFromRequest(request);
        if (token == null) {
            throw new NoTokenException("User not sign in");
        }
        UserEntity user = userEntityRepository.findByLogin(jwtProvider.getLoginFromToken(token));
        return user;
    }

}
