package com.web.springbootauth.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PrivilegeEntity {

    @Column(name = "PRIVILEGE_ID")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long privilegeId;

    @Column(name = "PRIVILEGE_NAME")
    private String privilegeName;

    public PrivilegeEntity(String privilegeName) {
        this.privilegeName = privilegeName;
    }
}
