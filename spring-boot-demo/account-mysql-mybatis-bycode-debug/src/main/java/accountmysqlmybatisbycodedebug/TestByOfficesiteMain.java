package accountmysqlmybatisbycodedebug;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestByOfficesiteMain {
    public static void main(String[] args) throws IOException {
        //test01();
        //test02();

        //test03();

        //test04();

//        test05();

        //test06();

        test07();
    }

    public static void test01() throws IOException {

        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        System.out.println("----------------------------");

        User user = sqlSession.selectOne("selectUserByName", "admin");

        System.out.println(user);

        System.out.println("----------------------------");

        user = sqlSession.selectOne("selectUserByName", "admin");

        System.out.println(user);

        sqlSession.close();
    }

    public static void test02() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Date now = new Date();
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName("user001");
        user.setPassword("user001@qwe");
        user.setCreatedTime(now);
        user.setUpdatedTime(now);

        int count = sqlSession.insert("insertUser", user);
        System.out.println("影响行数" + count);

        sqlSession.commit();

        sqlSession.close();
    }

    public static void test03() throws IOException {
        InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Map<String, String> updateMap = new HashMap<>();
        updateMap.put("password", "default@123");
        updateMap.put("userName", "user001");

        int count = sqlSession.update("updateUserPasswordByName", updateMap);
        System.out.println("影响行数" + count);

        sqlSession.commit();
        sqlSession.close();
    }

    public static void test04() throws IOException{
        InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        int delete = sqlSession.delete("deleteUserByName", "user001");
        System.out.println("影响行数" + delete);

        sqlSession.commit();
        sqlSession.close();

    }

    public static void test05() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        Configuration configuration = sessionFactory.getConfiguration();
        configuration.addMapper(RolesRepository.class);

        SqlSession sqlSession = sessionFactory.openSession();
        RolesRepository rolesRepository = sqlSession.getMapper(RolesRepository.class);

        Role role = new Role();
        role.setRoleId(UUID.randomUUID().toString());
        role.setRoleName("测试人员");
        int count = rolesRepository.insertRole(role);

        System.out.println("影响行数" + count);

        if(count == 1){
            sqlSession.commit();
        }

        sqlSession.close();

    }

    public static void test06() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        Configuration configuration = sessionFactory.getConfiguration();
        configuration.addMapper(RolesRepository.class);

        SqlSession sqlSession = sessionFactory.openSession();
        RolesRepository rolesRepository = sqlSession.getMapper(RolesRepository.class);

        Role role = rolesRepository.selectRoleByName("root");

        //模拟sql注入
        //Role role = rolesRepository.selectRoleByName("(select UserName from Users where UserId = 'b3768662-fa09-11e8-a20e-000c297eff23')");

        System.out.println(role);

        sqlSession.close();

    }

    //for debugging mybatis
    public static void test07() throws IOException{
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //必须放在 sqlSessionFactory.openSession() 之前
        sqlSessionFactory.getConfiguration().addInterceptor(new SqlInterceptor());

        //这里构建了运行时的需要的核心对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.getConfiguration().addMapper(UsersRepository.class);

        UsersRepository mapperProxy = sqlSession.getMapper(UsersRepository.class);

        System.out.println(mapperProxy);

        User user = mapperProxy.getUserByName("admin");
        System.out.println(user);

        sqlSession.close();
    }
}
