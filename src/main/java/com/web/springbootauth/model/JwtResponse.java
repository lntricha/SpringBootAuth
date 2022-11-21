package com.web.springbootauth.model;


import com.web.springbootauth.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;


    private UserDetails user;

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", user=" + user+
                '}';
    }
}
