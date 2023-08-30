package com.example.demo.data.entity;

import com.example.demo.data.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
@Table(name = "users")
public class User extends AbstractEntity {
    private String login;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Integer age;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
