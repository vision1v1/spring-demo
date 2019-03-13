package com.example.accountwebmysqlmybatis.repository;


import com.example.accountwebmysqlmybatis.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


//@Mapper
public interface UserRepository {

    @Select("select * from Users where UserId = #{userId}")
    User getUserById(String userId);

    @Select("select * from Users limit #{limit} offset #{offset}")
    List<User> getUsers(@Param("offset") int offset, @Param("limit") int limit);

    @Insert("insert into Users(UserId,UserName,Password,CreatedTime,UpdatedTime) values (#{userId},#{userName},#{password},#{createdTime},#{updatedTime})")
    int createUser(User user);

    @Update("update Users set UserName = #{userName}, Password = #{password}, UpdatedTime = #{updatedTime} where UserId = #{userId}")
    int updateUser(@Param("userId") String userId, @Param("userName") String userName, @Param("password") String password, @Param("updatedTime") Date updatedTime);

    @Delete("delete from Users where UserId = #{userId}")
    int deleteUser(@Param("userId") String userId);

}
