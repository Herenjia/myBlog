package org.example.myblog.controller;


import lombok.RequiredArgsConstructor;
import org.example.myblog.common.R;
import org.example.myblog.entity.User;
import org.example.myblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public R<Object> login(String username, String password) {
        return adminService.login(username, password);
    }


    @PostMapping("/register")
    public R<Object> register(@RequestBody User user) {
        return adminService.register(user);
    }

    @PostMapping("/me")
    public R<Object> getUser() {
        return adminService.getUser();
    }
}
