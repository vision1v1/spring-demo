package accountmysqlmybatisbycodedebug;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

//分析Mybatis核心对象，调试源码
public class AccountMySqlMybatisByCodeDebugMain {
    public static void main(String[] args) {
        //test01();

        //test02();

        //test03();

        test04();
    }

    @SuppressWarnings("Duplicates")
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

    //测试 mybatis 核心对象
    @SuppressWarnings("Duplicates")
    public static void test02() {
        try {
            //1.获取Configuration对象，并且配置
            String config = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(config);
            XMLConfigBuilder parser = new XMLConfigBuilder(reader, null, null);
            Configuration configuration = parser.parse();
            configuration.addMapper(UsersRepository.class);
            //configuration.addInterceptor(new MyInterceptor());

            //2.根据配置创建一个默认的SqlSessionFactory对象。
            SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);

            //3.通过工厂创建sqlSession对象。
            SqlSession session = sqlSessionFactory.openSession();

            //4.获取usersRepository对应MapperProxy代理类。
            UsersRepository usersRepository = session.getMapper(UsersRepository.class);

            //5.调用代理类中的方法。执行sql
            List<User> users = usersRepository.getAllUsers();

            users.forEach(user -> System.out.println(user));

        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试 mybatis 核心实现 硬编码配置
    @SuppressWarnings("Duplicates")
    public static void test03() {

        try {

            //1.configuration 初始化的实现，这里就是面向过程了，没有架构封装了。
            TransactionFactory txFactory = new JdbcTransactionFactory();
            DataSource dataSource = new PooledDataSource();
            ((PooledDataSource) dataSource).setDriver("com.mysql.cj.jdbc.Driver");
            //注意这里与配置文件中url的参数分割符的不同。
            ((PooledDataSource) dataSource).setUrl("jdbc:mysql://192.168.154.130:3306/Account?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            ((PooledDataSource) dataSource).setUsername("root");
            ((PooledDataSource) dataSource).setPassword("Admin@123");
            Environment environment = new Environment("dev123", txFactory, dataSource);
            Configuration configuration = new Configuration(environment);


            configuration.addMapper(UsersRepository.class);


            SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
            SqlSession session = sqlSessionFactory.openSession();

            //创建动态代理类
            UsersRepository usersRepository = session.getMapper(UsersRepository.class);


            List<User> users = usersRepository.getAllUsers();


            users.forEach(user -> System.out.println(user));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试 mybatis 核心实现
    @SuppressWarnings("Duplicates")
    public static void test04() {

        try {

            //1.configuration 初始化的实现，这里就是面向过程了，没有架构封装了。
            TransactionFactory txFactory = new JdbcTransactionFactory();
            DataSource dataSource = new PooledDataSource();
            ((PooledDataSource) dataSource).setDriver("com.mysql.cj.jdbc.Driver");
            //注意这里与配置文件中url的参数分割符的不同。
            ((PooledDataSource) dataSource).setUrl("jdbc:mysql://192.168.154.130:3306/Account?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            ((PooledDataSource) dataSource).setUsername("root");
            ((PooledDataSource) dataSource).setPassword("Admin@123");
            Environment environment = new Environment("dev123", txFactory, dataSource);
            Configuration configuration = new Configuration(environment);

            //创建MapperProxyFactory，解析UserRepository的标注信息，为创建动态代理类做准备。
            configuration.addMapper(UsersRepository.class);

            boolean autoCommit = false;
            Transaction tx = txFactory.newTransaction(environment.getDataSource(), null, autoCommit);
            //创建Executor
            Executor executor = configuration.newExecutor(tx, ExecutorType.SIMPLE);
            //创建 SqlSession 核心对象，ibatis中定义的sql执行行为。
            SqlSession session = new DefaultSqlSession(configuration, executor, autoCommit);
            //创建了动态代理类。
            UsersRepository usersRepository = session.getMapper(UsersRepository.class);


            //2.前面都是准备阶段，这里会执行sql。在执行sql中动态代理类，将生成的映射方法对象（MapperMethod）缓存起来。
            //方便下次使用。在交给SqlSession对象执行，SqlSession对象在交给Excutor执行。生成BoundSql对象，缓存起来。
            //然后在委托给SimpleExecutor执行doQuery。创建StatementHandler，StatementHandler创建Statement对象。
            //在由StatementHandler执行query，在由ibatis中RoutingStatementHandler路由到PreparedStatement中执行。
            //在由StatementHandler处理结果集合。
            List<User> users = usersRepository.getAllUsers();

            users.forEach(user -> System.out.println(user));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
