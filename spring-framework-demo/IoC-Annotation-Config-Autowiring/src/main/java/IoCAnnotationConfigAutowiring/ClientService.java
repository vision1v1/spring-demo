package IoCAnnotationConfigAutowiring;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class ClientService {
    //private static ClientService clientService = new ClientService();

    public ClientService(){
        System.out.println("ClientService Created");
    }

//    public static ClientService createInstance(){
//        System.out.println("静态工厂方法调用");
//        return clientService;
//    }

    public int add(int a,int b){
        return a+b;
    }
}
