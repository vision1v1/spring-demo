package accountmysqlmybatisbycodedebug;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

//这个类必须是public 要不@SelectProvider中解析报错
public class RoleSqlBuilder {

    //这种写法可以避免sql注入
    public static String buildGetRoleByName(@Param("name") String name) {
        SQL sql = new SQL();
        sql.SELECT("RoleId","RoleName").FROM("Roles").WHERE("RoleName = #{name}");
        System.out.println("**********"+ sql + "****************");
        return sql.toString();
    }

    //这种写法容易产生sql注入现象
    public static String buildGetRoleByName2(@Param("name") String roleName){
        String sql = "select RoleId,RoleName from Roles where RoleName = '" + roleName + "'";
        System.out.println("**********"+ sql + "****************");
        return sql;
    }

    //模拟sql注入
    public static String buildGetRoleByName3(@Param("name") String roleName){
        String sql = "select RoleId,RoleName from Roles where RoleName = " + roleName ;
        System.out.println("**********"+ sql + "****************");
        return sql;
    }
}
