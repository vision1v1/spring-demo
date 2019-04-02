package transaction;

import org.apache.ibatis.annotations.Insert;

public interface User1Mapper {
    @Insert("insert into user1(id,name) values(#{id},#{name})")
    int insertUser1(User1 user1);
}
