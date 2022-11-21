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

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PrivilegeEntity implements Serializable {

    @Column(name = "PRIVILEGE_ID")
    @Id
    private Long privilegeId;

    @Column(name = "PRIVILEGE_NAME")
    private String privilegeName;

    @ManyToMany(mappedBy = "privileges")
    @JsonIgnore
    private Set<RoleEntity> roles;

    public PrivilegeEntity(Long privilegeId,String privilegeName,Set<RoleEntity> roles) {

        this.privilegeId = privilegeId;
        this.privilegeName = privilegeName;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "PrivilegeEntity{" +
                "privilegeId=" + privilegeId +
                ", privilegeName='" + privilegeName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
