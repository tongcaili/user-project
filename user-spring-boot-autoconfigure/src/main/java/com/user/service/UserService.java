package com.user.service;


import com.user.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;


import java.util.List;

public interface UserService {
    User save(User user);

    Page<User> page(Pageable pageable);

    @Nullable
    User findOne(Integer id);

    User update(User user);

    void delete(Integer id);

    User login(String username,String password);

    List<String> loginUsers();
}
