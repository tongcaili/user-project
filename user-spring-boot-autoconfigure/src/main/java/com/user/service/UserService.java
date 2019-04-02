package com.user.service;

import com.user.pojo.User;
import com.user.vo.R;
import org.springframework.data.domain.Pageable;

public interface UserService {
    R save(User user);

    R page(Pageable pageable);

    R findOne(Integer id);

    R update(User user);

    R delete(Integer id);

    R login(User user);

    R loginUsers();
}
