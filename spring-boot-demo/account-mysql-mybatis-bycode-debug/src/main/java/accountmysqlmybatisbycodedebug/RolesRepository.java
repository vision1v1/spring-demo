package accountmysqlmybatisbycodedebug;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

//@CacheNamespace()
public interface RolesRepository {

    @Insert({"insert into Roles(RoleId,RoleName) values(#{roleId},#{roleName})"})
        //@Options(keyProperty = "roleId")
        //@SelectKey(statement = "select UserId as roleId from Users where UserName = 'root'", keyProperty = "UserId",before = true,resultType = String.class)
    int insertRole(Role role);


    @SelectProvider(type=RoleSqlBuilder.class,method = "buildGetRoleByName")
    Role selectRoleByName(@Param("name") String name);
}

