package accountmysqlmybatisbycodedebug;

import org.apache.ibatis.annotations.*;

import java.util.List;

//缓存Namespace写法，也叫二级缓存
@CacheNamespaceRef
public interface UsersRepository {

    @Select("select * from Users")
    @Results(value={
            @Result(property = "userId",column = "UserId"),
            @Result(property = "userName",column = "UserName"),
            @Result(property = "password",column = "Password"),
            @Result(property = "createdTime",column = "CreatedTime"),
            @Result(property = "updatedTime",column = "UpdatedTime"),
    })
    List<User> getAllUsers();

    @Select({"select * from Users where userName=#{userName123}"})
    @Results(value={
            @Result(property = "userId",column = "UserId"),
            @Result(property = "userName",column = "UserName"),
            @Result(property = "password",column = "Password"),
            @Result(property = "createdTime",column = "CreatedTime"),
            @Result(property = "updatedTime",column = "UpdatedTime"),
    })

    //flushCache=Options.FlushCachePolicy.TRUE 可以关闭一级缓存。
    //@Options(flushCache=Options.FlushCachePolicy.TRUE)
    User getUserByName(String userName123);

}
