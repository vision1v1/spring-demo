package usage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringMybatisUsageMain {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserMapper userMapper = context.getBean("userMapper",UserMapper.class);

        System.out.println("-------------------------------------------");

        System.out.println(userMapper.getUser("b3768662-fa09-11e8-a20e-000c297eff23"));

        System.out.println("-------------------------------------------");

        System.out.println(userMapper.getUser("b3768662-fa09-11e8-a20e-000c297eff23"));

    }
}
