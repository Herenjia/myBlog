package org.example.myblog.service;

import lombok.extern.slf4j.Slf4j;
import org.example.myblog.common.R;
import org.example.myblog.entity.User;
import org.example.myblog.repo.UserRepo;
import org.example.myblog.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;
    public R<Object> login(String username, String password) {
        Optional<User> opt = userRepo.findByUsername(username);
        if (opt.isPresent()) {
            User user = opt.get();
            log.info(username + "正在登录");
            if (password.equals(user.getPassword())) {
                String token = JWTUtil.generateTokenForUser(user);
                return R.ok(token);
            } else {
                return R.failed(username + "的密码错误");
            }
        } else {
            return R.failed(username + "该用户名不存在");
        }
    }

    public R<Object> register(User user) {
        return R.ok(userRepo.save(user));
    }

    public R<Object> getUser() {
    }
}
