package com.user.service.impl;

import com.user.exception.UserNotFoundException;
import com.user.pojo.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import com.user.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final StringRedisTemplate template;

    private static final String HEAD = "user:online:";


    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String salt = "rescxezvvry3456t";

    public UserServiceImpl(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") UserRepository userRepository, StringRedisTemplate template) {
        this.userRepository = userRepository;
        this.template = template;
    }

    @Override
    public User save(User user) {
        user.setPassword(Md5Util.md5Encodeutf8(user.getPassword(), salt));//密码加密
        return userRepository.save(user);
    }

    @Override
    public Page<User> page(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findOne(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {//用户不存在
            //todo 用户不存在抛出异常
            throw new UserNotFoundException("用户不存在");
        }
        return optional.get();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String username, String password) {
        password = Md5Util.md5Encodeutf8(password, salt);
        Optional<User> optional = userRepository.findByUsernameAndPassword(username, password);
        if (!optional.isPresent()) {
            throw new UserNotFoundException("用户名或密码错误");
        }
        User user = optional.get();
        //将用户id加入redis
        template.opsForValue().set(HEAD + user.getId(), user.getId().toString(), 100, TimeUnit.SECONDS);
        return user;
    }

    @Override
    public List<String> loginUsers() {
        Set<String> keys = template.keys(HEAD + '*');
        List<String> users = new ArrayList<>();
        for (String key : keys) {
            users.add(key);
        }
        return users;
    }

}
