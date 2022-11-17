package com.web.springbootauth.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Column(name = "USER_ID")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @OneToOne(cascade = ALL, targetEntity = RoleEntity.class)
    @JoinColumn(name = "ROLE_DESCRIPTION", referencedColumnName = "USER_ID")
    private RoleEntity roleDescription;

    public UserEntity(String firstName, String lastName, String emailId, RoleEntity roleDescription) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.roleDescription = roleDescription;
    }
}
