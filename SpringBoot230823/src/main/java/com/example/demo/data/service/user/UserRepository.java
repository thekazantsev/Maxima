package com.example.demo.data.service.user;

import com.example.demo.data.entity.User;
import com.example.demo.data.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByLogin(String login);

    List<User> findAllByAge(Integer age);

    List<User> findAllByRole(Role role);
}
