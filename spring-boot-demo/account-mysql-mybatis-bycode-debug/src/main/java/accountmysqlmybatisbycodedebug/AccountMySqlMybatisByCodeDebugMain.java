package accountmysqlmybatisbycodedebug;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class AccountMySqlMybatisByCodeDebugMain {
    public static void main(String[] args) {
        test01();
    }

    public static void test01() {

        try {
            String config = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(config);

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();

            Configuration configuration = session.getConfiguration();
            configuration.addMapper(UsersRepository.class);

            UsersRepository usersRepository = session.getMapper(UsersRepository.class);

            List<User> users = usersRepository.getAllUsers();
            users.forEach(user -> System.out.println(user));

        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
