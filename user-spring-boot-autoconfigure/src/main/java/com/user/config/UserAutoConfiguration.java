package com.user.config;

import com.user.pojo.User;
import com.user.service.UserService;
import com.user.service.impl.UserServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.user.repository")
@EntityScan("com.user.pojo")
public class UserAutoConfiguration {

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

}
