package IoCAnnotationConfigAutowiring;

import org.springframework.beans.factory.annotation.Autowired;

public class MyController01 {

    @Autowired
    private MyService01 myService01;

    public String getService(){
        return myService01.getObjectInfo();
    }
}
