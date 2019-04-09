package com.user.controller;

import com.user.exception.ParamException;
import com.user.pojo.User;
import com.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "用户")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/save")
    @ApiOperation("保存用户")
    public User save(@Validated @RequestBody User user) {
        if (user.getId() != null) {
            throw new ParamException("参数错误");
        }
        return userService.save(user);
    }

    @GetMapping("/list")
    @ApiOperation("分页查询用户列表")
    public Page<User> page(@PageableDefault Pageable pageable) {
        return userService.page(pageable);
    }

    @GetMapping("/get")
    @ApiOperation("根据id查询用户")
    public User findOne(@RequestParam Integer id) {
        return userService.findOne(id);
    }

    @PutMapping("/update")
    @ApiOperation("更新用户")
    public User update(@RequestBody User user) {
        if (user.getId() == null) {
            throw new ParamException("id不能为空");
        }
        return userService.update(user);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除单个用户")
    public void delete(@RequestParam Integer id) {
        userService.delete(id);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public User login(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");
        Assert.notNull(username, "用户名不能为空");
        Assert.notNull(password, "密码不能为空");
        return userService.login(user.get("username"), user.get("password"));
    }

    @GetMapping("/login_user")
    @ApiOperation("查询在线用户的id")
    public List<String> loginUserList() {
        return userService.loginUsers();
    }
}
