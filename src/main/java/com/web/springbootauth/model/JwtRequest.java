package com.web.springbootauth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    private String userName;
    private String password="richa123";

    @Override
    public String toString() {
        return "JwtRequest{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
