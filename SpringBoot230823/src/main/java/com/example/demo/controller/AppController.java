package com.example.demo.controller;

import com.example.demo.data.entity.User;
import com.example.demo.data.enums.Role;
import com.example.demo.data.service.user.UserService;
import com.example.demo.service.AppService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {

    private final AppService appService;
    private final UserService userService;

    AppController(AppService appService, UserService userService) {
        this.appService = appService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "Hello";
    }

    @GetMapping("/getwithparam")
    public String getWithParam(@RequestParam String param) {
        return "Your GET param: " + param;
    }

    @PostMapping("/post")
    public ResponseEntity<String> post(@RequestBody String name) {
        return appService.post(name);
    }

    @PostMapping("/postuser")
    public ResponseEntity<String> postUser(@RequestBody String userName) {
        User user = new User();
        user.setLogin(userName);
        user.setRole(Role.USER);
        user.setAge(38);
        User newUser = userService.update(user);

//        User userByLogin = userService.findByLogin(userName);

        return ResponseEntity.ok(newUser.getLogin());
    }
}
