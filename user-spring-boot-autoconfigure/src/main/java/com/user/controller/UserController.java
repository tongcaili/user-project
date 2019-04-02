package com.user.controller;

import com.user.pojo.User;
import com.user.service.UserService;
import com.user.vo.R;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/save")
    public R save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/list")
    public R page(@PageableDefault Pageable pageable) {
        return userService.page(pageable);
    }

    @GetMapping("/get")
    public R findOne(@RequestParam Integer id) {
        return userService.findOne(id);
    }

    @PutMapping("/update")
    public R update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/delete")
    public R delete(@RequestParam Integer id) {
        return userService.delete(id);
    }

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/login_user")
    public R loginUserList() {
        return userService.loginUsers();
    }
}
