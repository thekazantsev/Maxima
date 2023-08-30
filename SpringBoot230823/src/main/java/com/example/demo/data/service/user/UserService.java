package com.example.demo.data.service.user;

import com.example.demo.data.entity.User;
import com.example.demo.data.enums.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService (UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    public User update(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public List<User> findAllByAge(Integer age) {
        return repository.findAllByAge(age);
    }

    public List<User> findAllByRole(Role role) {
        return repository.findAllByRole(role);
    }
}
