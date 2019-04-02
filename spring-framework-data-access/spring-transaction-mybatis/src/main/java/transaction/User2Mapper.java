package transaction;

import org.apache.ibatis.annotations.Insert;

public interface User2Mapper {
    @Insert("insert into user2(id,name) values(#{id},#{name})")
    int insertUser2(User2 user2);
}
