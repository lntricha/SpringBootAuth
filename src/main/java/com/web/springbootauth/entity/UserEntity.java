package com.web.springbootauth.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity implements UserDetails, Serializable {

    @Column(name = "USER_ID")
    @Id
    private Long userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @ManyToOne()
    @JoinColumn(name="ROLE_ID", referencedColumnName = "ROLE_ID", insertable = true, updatable = true)
    private RoleEntity roleDescription;



    public UserEntity(Long userId,String firstName, String lastName, String emailId, RoleEntity roleDescription) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.roleDescription = roleDescription;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set authorities= new HashSet();

        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.getRoleDescription().getRoleName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", roleDescription=" + roleDescription +
                '}';
    }
}
