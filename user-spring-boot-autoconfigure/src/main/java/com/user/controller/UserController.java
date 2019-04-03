package com.user.controller;

import com.user.exception.ParamException;
import com.user.pojo.User;
import com.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public ResponseEntity<User> save(@Validated @RequestBody User user) {
        if (user.getId() != null) {
            throw new ParamException("参数错误");
        }
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<User>> page(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.page(pageable));
    }

    @GetMapping("/get")
    public ResponseEntity<User> findOne(@RequestParam Integer id) {
        return ResponseEntity.ok(userService.findOne(id));
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        if (user.getId() == null) {
            throw new ParamException("id不能为空");
        }
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody String username,String password) {
        return ResponseEntity.ok().body(userService.login(username, password));
    }

    @GetMapping("/login_user")
    public ResponseEntity<List<String>> loginUserList() {
        return ResponseEntity.ok().body(userService.loginUsers());
    }
}
