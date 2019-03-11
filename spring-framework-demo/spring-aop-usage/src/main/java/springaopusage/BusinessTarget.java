package springaopusage;

import org.springframework.stereotype.Component;

//@Component
public class BusinessTarget {

    public void transfer(){
        System.out.println("BusinessTarget transfer");
    }

    public void transfer(String name){
        System.out.println("BusinessTarget transfer " + name);
    }

}
