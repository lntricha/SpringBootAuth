package com.web.springbootauth.repository;

import com.web.springbootauth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

//    UserEntity findByUserEmailId(String emailId);
    UserEntity findByEmailId(String emailId);
}
