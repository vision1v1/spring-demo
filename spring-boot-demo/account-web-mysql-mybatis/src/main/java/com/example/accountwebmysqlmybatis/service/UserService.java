package com.example.accountwebmysqlmybatis.service;


import com.example.accountwebmysqlmybatis.dto.CreateUserInput;
import com.example.accountwebmysqlmybatis.dto.UpdateUserInput;
import com.example.accountwebmysqlmybatis.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(String userId);

    List<User> getUserList(int pageNum, int pageSize);

    String createUser(CreateUserInput input);

    boolean updateUser(UpdateUserInput input);

    boolean deleteUser(String userId);
}
