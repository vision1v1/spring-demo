package usage;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;

public class MyMapperFactoryBean<T> extends MapperFactoryBean {

    private static SqlSessionTemplate sqlSessionTemplate;

    public MyMapperFactoryBean(){
        super();
    }

    public MyMapperFactoryBean(Class<T> mapperInterface){
        super(mapperInterface);
    }

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        //super.setSqlSessionFactory(sqlSessionFactory);

        if(sqlSessionTemplate == null){
            sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        }
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
}
