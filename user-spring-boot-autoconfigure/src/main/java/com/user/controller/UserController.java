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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*  @GetMapping("/hello")
      public ResponseEntity<String> hello() {
          return ResponseEntity.ok("hello");
      }
    */
    @PostMapping("/save")
    @ApiOperation("保存用户")
    public ResponseEntity<User> save(@Validated @RequestBody User user) {
        if (user.getId() != null) {
            throw new ParamException("参数错误");
        }
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/list")
    @ApiOperation("分页查询用户列表")
    public ResponseEntity<Page<User>> page(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.page(pageable));
    }

    @GetMapping("/get")
    @ApiOperation("根据id查询用户")
    public ResponseEntity<User> findOne(@RequestParam Integer id) {
        return ResponseEntity.ok(userService.findOne(id));
    }

    @PutMapping("/update")
    @ApiOperation("更新用户")
    public ResponseEntity<User> update(@RequestBody User user) {
        if (user.getId() == null) {
            throw new ParamException("id不能为空");
        }
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除单个用户")
    public ResponseEntity<Void> delete(@RequestParam Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    public ResponseEntity<User> login( String username,String password) {
        return ResponseEntity.ok().body(userService.login(username, password));
    }

    @GetMapping("/login_user")
    @ApiOperation("查询在线用户的id")
    public ResponseEntity<List<String>> loginUserList() {
        return ResponseEntity.ok().body(userService.loginUsers());
    }
}
