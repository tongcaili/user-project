package com.user.service.impl;

import com.user.pojo.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import com.user.utils.Md5Util;
import com.user.vo.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StringRedisTemplate template;

    private static final String HEAD = "user:online:";


    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String salt = "rescxezvvry3456t";

    @Override
    public R save(User user) {
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername.isPresent()) {
            return R.errMsg("用户名已存在!");
        }
        user.setPassword(Md5Util.md5Encodeutf8(user.getPassword(), salt));//密码加密

        try {
            userRepository.save(user);
        } catch (Exception e) {
            return R.errMsg("创建失败", e.getMessage());
        }
        LOGGER.info("创建用户成功: {}", user.getUsername());
        return R.okMsg("创建成功");
    }

    @Override
    public R page(Pageable pageable) {
        Page<User> all = userRepository.findAll(pageable);
        return R.ok(all);
    }

    @Override
    public R findOne(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            return R.errMsg("用户名不存在");
        }
        return R.ok("查询成功", optional.get());
    }

    @Override
    public R update(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return R.errMsg("修改失败", e.getMessage());
        }
        LOGGER.info("修改用户成功: {}", user.getUsername());
        return R.okMsg("修改成功");
    }

    @Override
    public R delete(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return R.errMsg("删除失败", e.getMessage());
        }
        LOGGER.info("删除用户成功");
        return R.okMsg("删除用户成功");
    }

    @Override
    public R login(User user) {
        user.setPassword(Md5Util.md5Encodeutf8(user.getPassword(), salt));
        Optional<User> optional = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (!optional.isPresent()) {
            return R.errMsg("用户名或密码错误");
        }
        template.opsForValue().set(HEAD + optional.get().getId(), optional.get().getId().toString(), 100, TimeUnit.SECONDS);
        return R.ok("登录成功", user);
    }

    @Override
    public R loginUsers() {
        Set<String> keys = template.keys(HEAD + '*');
        List<String> users = new ArrayList<>();
        if (keys != null) {
            for (String key : keys) {
                users.add(key);
            }
        }
        return R.ok(users);
    }

}
