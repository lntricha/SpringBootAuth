package com.web.springbootauth.service;

import com.web.springbootauth.entity.UserEntity;
import com.web.springbootauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    public UserEntity loadUserByUserName(String emailId){
//        UserEntity userEntity;
//        userEntity = userRepository.findByUserEmailId(emailId);
//        return userEntity;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity;
        userEntity = userRepository.findByEmailId(username);
        if(userEntity!=null)
        return userEntity;
        else
            throw new UsernameNotFoundException("User not found");
    }
}
