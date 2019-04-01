package usage;


import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//@MapperScan("usage")
//@MapperScan(value = "usage",factoryBean = MyMapperFactoryBean.class)
public class AppConfig {

    @Bean
    DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("Admin@123");
        dataSource.setUrl("jdbc:mysql://192.168.209.131:3306/Account?useSSL=false");
        return dataSource;
    }


    //第一种配置
//    @Bean
//    SqlSessionFactoryBean getSqlSessionFactoryBean(@Autowired DataSource dataSource){
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        return sqlSessionFactoryBean;
//    }

    //第二种配置方式
    @Bean
    SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
        return sqlSessionFactory;
    }


    //@Bean("transactionManager")
    @Bean
    DataSourceTransactionManager dataSourceTransactionManager(@Autowired DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    UserMapper userMapper(@Autowired SqlSessionFactory sqlSessionFactory){
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        UserMapper userMapper = sessionTemplate.getMapper(UserMapper.class);
        return userMapper;
    }



}
