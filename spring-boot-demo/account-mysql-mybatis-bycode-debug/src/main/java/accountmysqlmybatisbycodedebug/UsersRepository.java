package accountmysqlmybatisbycodedebug;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("select * from Users where userName=#{userName123}")
    @Results(value={
            @Result(property = "userId",column = "UserId"),
            @Result(property = "userName",column = "UserName"),
            @Result(property = "password",column = "Password"),
            @Result(property = "createdTime",column = "CreatedTime"),
            @Result(property = "updatedTime",column = "UpdatedTime"),
    })
    User getUserByName(String userName123);

}
