package transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class User1Service {

    @Autowired
    private User1Mapper user1Mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User1 user1){
        user1Mapper.insertUser1(user1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNew(User1 user1){
        user1Mapper.insertUser1(user1);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addNested(User1 user1){
        user1Mapper.insertUser1(user1);
    }
}
