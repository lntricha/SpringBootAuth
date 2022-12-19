package com.web.springbootauth.model;

import com.web.springbootauth.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtUpdateRequest {
    private UserEntity userEntity;

    @Override
    public String toString() {
        return "JwtUpdateRequest{" +
                "userEntity=" + userEntity +
                '}';
    }
}
