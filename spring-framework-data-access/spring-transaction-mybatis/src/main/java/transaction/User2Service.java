package transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class User2Service {

    @Autowired
    private User2Mapper user2Mapper;


    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User2 user2){
        user2Mapper.insertUser2(user2);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(User2 user2){
        user2Mapper.insertUser2(user2);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNew(User2 user2){
        user2Mapper.insertUser2(user2);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNewException(User2 user2){
        user2Mapper.insertUser2(user2);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addNested(User2 user2){
        user2Mapper.insertUser2(user2);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addNestedException(User2 user2){
        user2Mapper.insertUser2(user2);
        throw new RuntimeException();
    }

}
