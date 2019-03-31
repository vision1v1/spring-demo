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
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

//分析Mybatis核心对象，调试源码
public class AccountMySqlMybatisByCodeDebugMain {
    public static void main(String[] args) {

        //test00();

        //test01();

        //test02();

        //test03();

        //test04();

        test05();

        //test06();
    }

    //直接执行
    @SuppressWarnings("Duplicates")
    public static void test00() {

        try {
            String config = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(config);

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();

            Configuration configuration = session.getConfiguration();

            //1，可以直接在configuration中添加mapper。
            //2，也可以@Mapper 利用@MapperScan扫描进去到configuration中。spring boot这么搞的
            configuration.addMapper(UsersRepository.class);

            //ibatis的写法
            List<User> users = session.selectList("accountmysqlmybatisbycodedebug.UsersRepository.getAllUsers");
            users.forEach(user -> System.out.println(user));

        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //mapper的方式执行sql
    @SuppressWarnings("Duplicates")
    public static void test01() {

        try {
            String config = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(config);

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();

            Configuration configuration = session.getConfiguration();


            //1.addMapper   -> 将被代理的类（即被标记为@Mapper的类）封装为MapperProxyFactory，
            // 根据对应的type保存到Configuration中的MappRegistry中。（将来用于创建动态代理）。
            // 然后解析（@Mapper）目标类中的标记例如@Select，将解析结果放到Configuration中的mappedStatements中，这里是将被标记的方法名，与sql语句map了一起。
            // 为后续动态代理MapperProxy的InvocationHandler调用。
            configuration.addMapper(UsersRepository.class);

            //2.getMapper   -> 中根据1中MapperProxyFactory，创建动态代理 MapperProxy。
            UsersRepository usersRepository = session.getMapper(UsersRepository.class);

            //3.调用要代理的接口后，调用到代理，在代理中执行invoke调用，将mapperInterface与调用的接口方法封装成MapperMethod对象，
            // 缓存这个对象 到MapperProxy中，然后执行mapperMethod方法 利用SqlSession执行sql语句。（ibatis的用法）
            List<User> users = usersRepository.getAllUsers();
            users.forEach(user -> System.out.println(user));

            //查不到，返回null
            User user = usersRepository.getUserByName("ssy");
            System.out.println(user);

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

    //家里测试mybatis 通过硬编码的方式配置
    @SuppressWarnings("Duplicates")
    public static void test05(){
        try {

            //1.准备环境对象，为配置对象Configuration做准备。
            TransactionFactory txFactory = new JdbcTransactionFactory();
            //TransactionFactory txFactory = new ManagedTransactionFactory();
            //这个在 mybatis-spring 下
            //TransactionFactory txFactory = new SpringManagedTransactionFactory();

            DataSource dataSource = new PooledDataSource();
            ((PooledDataSource) dataSource).setDriver("com.mysql.cj.jdbc.Driver");
            //注意这里与配置文件中url的参数分割符的不同。
            ((PooledDataSource) dataSource).setUrl("jdbc:mysql://192.168.209.131:3306/Account?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            ((PooledDataSource) dataSource).setUsername("root");
            ((PooledDataSource) dataSource).setPassword("Admin@123");
            Environment environment = new Environment("dev123", txFactory, dataSource);

            //2.生成Configuration对象，添加mapper对应的类。
            Configuration configuration = new Configuration(environment);
            configuration.addMapper(UsersRepository.class);
            //添加sql的拦截器，用于显示执行的sql语句与执行时间。
            configuration.addInterceptor(new SqlInterceptor());

            //设置LocalCache，本地缓存（一级缓存）为语句级别，这样每次sql都是最新
            //configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);


            SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //3.根据前面configuration中配置的mapper信息，创建动态代理类。
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);

            System.out.println("*********************************");

            //4.根据动态代理类。执行Executor中对应的方法。执行sql。
            List<User> users1 = usersRepository.getAllUsers();

            users1.forEach(user -> System.out.println(user));


            System.out.println("---------------------------------");

            User user1 = usersRepository.getUserByName("ssy");

            System.out.println(user1);

            System.out.println("---------------------------------");

            User user2 = usersRepository.getUserByName("ssy");

            System.out.println(user2);

            System.out.println("---------------------------------");

            //缓存返回同一个对象，这个对象在缓存中，外部修改会有问题。
            // Note that when the localCacheScope is set to SESSION,
            // MyBatis returns references to the same objects which are stored in the local cache.
            // Any modification of returned object (lists etc.) influences the local cache contents
            // and subsequently the values which are returned from the cache in the lifetime of the session.
            // Therefore, as best practice, do not to modify the objects returned by MyBatis.
            System.out.println("user1 == user2 " + ( user1==user2 ) + " " + user1.equals(user2));

//            User user2 = sqlSession.selectOne("accountmysqlmybatisbycodedebug.UsersRepository.getUserByName","admin");
//
//            System.out.println(user2);

            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //测试mybatis中一级缓存 local session caching
    @SuppressWarnings("Duplicates")
    public static void test06() {

        org.apache.ibatis.logging.LogFactory.useLog4J2Logging();
        try {
            String config = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(config);

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();

            Configuration configuration = session.getConfiguration();
            configuration.addMapper(UsersRepository.class);
            UsersRepository usersRepository = session.getMapper(UsersRepository.class);

//            User user = usersRepository.getUserByName("admin");
//            System.out.println(user);
//            System.out.println("--------默认开启了一级缓存-------");
//            user = usersRepository.getUserByName("admin");
//            System.out.println(user);
            //DefaultSqlSession
            User user1 = session.selectOne("getUserByName","admin");
            System.out.println(user1);
            System.out.println("--------默认开启了一级缓存-------");
            //如果sql以及参数不变，一级缓存打开后，不会在查数据库，直接从本地缓存返回数据。
            //这就有数据不一致的问题。
            User user2 = session.selectOne("getUserByName","admin");
            System.out.println(user2);

            //缓存返回同一个对象，这个对象在缓存中，外部修改会有问题。
            System.out.println("user1 == user2 " + ( user1==user2 ));

        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //二级缓存，就是两个标记了@Mapper的类中执行的查询用了一个缓存。前提是相同的namespace。
    public static void test07(){

    }
}
