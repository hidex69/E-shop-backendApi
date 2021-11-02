package com.eshop.demo.config;

import com.eshop.demo.models.user.UserEntity;
import com.eshop.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findByLogin(login);
        return CustomUserDetails.fromUserEntityToCustumUserDetails(userEntity);
    }
}
