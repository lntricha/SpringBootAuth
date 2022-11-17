package com.web.springbootauth.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity {

    @Column(name = "ROLE_ID")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @OneToMany(cascade = ALL, targetEntity = PrivilegeEntity.class)
    @JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "ROLE_ID")
    private List<PrivilegeEntity> privileges;

    public RoleEntity(String roleName, List<PrivilegeEntity> privileges) {
        this.roleName = roleName;
        this.privileges = privileges;
    }
}
