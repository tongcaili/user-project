package com.user.config;

import com.user.controller.UserController;
import com.user.service.impl.UserServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.user.repository")
@EntityScan("com.user.pojo")
@Import({UserServiceImpl.class,UserController.class})
public class UserAutoConfiguration {

    public UserAutoConfiguration() {

    }
}
