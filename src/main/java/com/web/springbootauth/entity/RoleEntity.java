package com.web.springbootauth.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity implements Serializable {

    @Column(name = "ROLE_ID")
    @Id
    private Long roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @JsonIgnore
    @OneToMany(mappedBy = "roleDescription", fetch = FetchType.LAZY)
    private Set<UserEntity> users;

    @ManyToMany
    @JoinTable(
            name = "given_privilege",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_ID"))
    private Set<PrivilegeEntity> privileges;

    public RoleEntity(Long roleId,String roleName, Set<PrivilegeEntity> privileges) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.privileges = privileges;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                ", privileges=" + privileges +
                '}';
    }
}
