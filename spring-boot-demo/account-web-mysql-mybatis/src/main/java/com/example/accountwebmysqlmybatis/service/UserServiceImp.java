package com.example.accountwebmysqlmybatis.service;

import com.example.accountwebmysqlmybatis.dto.CreateUserInput;
import com.example.accountwebmysqlmybatis.dto.UpdateUserInput;
import com.example.accountwebmysqlmybatis.entity.User;
import com.example.accountwebmysqlmybatis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(String userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public List<User> getUserList(int pageNum, int pageSize) {
        return userRepository.getUsers((pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public String createUser(CreateUserInput createUserInput) {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName(createUserInput.getUserName());
        user.setPassword(createUserInput.getPassword());
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //dateFormat.format(date);
        user.setCreatedTime(date);
        user.setUpdatedTime(date);
        int rowAffected = userRepository.createUser(user);
        return rowAffected == 1 ? user.getUserId() : "";
    }

    @Override
    public boolean updateUser(UpdateUserInput input) {
        int rowAffected = userRepository.updateUser(input.getUserId(), input.getUserName(), input.getPassword(), new Date());
        return rowAffected == 1;
    }

    @Override
    public boolean deleteUser(String userId) {
        int rowAffected = userRepository.deleteUser(userId);
        return rowAffected == 1;
    }


}
