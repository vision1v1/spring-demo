package usage;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {
    @Select("select * from Users where UserId = #{userId}")
    User getUser(@Param("userId") String userId);
}
