package com.example.demo.controller;

import com.example.demo.data.entity.Certificate;
import com.example.demo.data.entity.User;
import com.example.demo.data.enums.Role;
import com.example.demo.data.service.user.UserService;
import com.example.demo.service.AppService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@RestController
public class AppController {

    private final AppService appService;
    private final UserService userService;

    AppController(AppService appService, UserService userService) {
        this.appService = appService;
        this.userService = userService;
    }

    @RequestMapping("/")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }

    @GetMapping("/getwithparam")
    public String getWithParam(@RequestParam String param) {
        return "Your GET param: " + param;
    }

    @PostMapping("/post")
    public ResponseEntity<String> post(@RequestBody String name) {
        return appService.post(name);
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> postUser(@RequestBody String userName) {
        User user = new User();
        user.setLogin(userName);
        user.setRole(Role.USER);
        user.setAge(38);
        Certificate cert = new Certificate();
        cert.setNumber(new Random().nextInt(99999999));
        cert.setSerial(new Random().nextInt(999));
        user.setCertificate(cert);

        userService.update(user);

        User userByLogin = userService.findByLogin(userName);

        return ResponseEntity.ok(userByLogin.getLogin());
    }
}
