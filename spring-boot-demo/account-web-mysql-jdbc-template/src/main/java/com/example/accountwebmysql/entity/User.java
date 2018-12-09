package com.example.accountwebmysql.entity;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User implements RowMapper<User>, Serializable {
    private String userId;
    private String userName;
    private String password;
    private Date createdTime;
    private Date updatedTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getString("UserId"));
        user.setUserName(resultSet.getString("UserName"));
        user.setPassword(resultSet.getString("Password"));
        user.setCreatedTime(resultSet.getDate("CreatedTime"));
        user.setUpdatedTime(resultSet.getDate("UpdatedTime"));
        return user;
    }
}
