package transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionalTestMain {

    @Autowired User1Service user1Service;

    @Autowired User2Service user2Service;

    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        TransactionalTestMain testMain = context.getBean(TransactionalTestMain.class);

        //testMain.notransaction_exception_required_required();

        //testMain.notranscation_required_requied_exception();

        //testMain.transaction_exception_requied_required();

        //testMain.transaction_required_required_exception();

        //testMain.transaction_required_required_exception_try();

        //testMain.notransaction_exception_requiresNew_requiresNew();

        //testMain.notranaction_requiresNew_requiresNew_exception();

        //testMain.transaction_exception_required_requiresNew_requireNew();

        //testMain.transaction_required_requiresNew_requiresNew_exception();

        //testMain.transaction_required_requiresNew_requiresNew_exception_try();

        //testMain.notransaction_exception_nested_nested();

        //testMain.notransaction_nested_nested_exception();

        //testMain.transaction_exception_nested_nested();

        //testMain.transaction_nested_nested_exception();

        testMain.transaction_nested_nested_exception_try();
    }


    //外围没有开启事务，内部user1Service开启一个新事务1，user2Service开启一个新事务2
    //外围抛出一个异常，不会影响事务1，事务2。
    //运行结果 zhang shan 插入，li shi 插入。
    @SuppressWarnings("Duplicates")
    public void notransaction_exception_required_required(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }

    //外围没有开启事务，内部user1Service开启一个新事务1，user2Service开启一个新事物2
    //user2Service中有异常产生，事务2感知，回滚事务2.事务1与事务2是两个独立的事务。所以事务1不受到影响。
    //运行结果 zhang shan 插入，li shi 未插入
    @SuppressWarnings("Duplicates")
    public void notranscation_required_requied_exception(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addRequiredException(user2);
    }

    //外围开启一个事务0，内部user1Service加入到外围事务0中，user2Service加入到外围事务0中。
    //外围方法中抛出一个异常，外围事务感知到回滚事务0.导致 zhang shan 数据与 li shi 数据回滚掉。
    //运行结果：zhang shan 与 li shi 均未插入。
    @SuppressWarnings("Duplicates")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_requied_required(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }


    // 外围开启一个事务0，user1Service加入到事务0中，user2Service加入到事务0中，
    // user2Service执行过程中产生了异常，由于已经加入到事务0中了，外围事务感知到了这个异常。
    // 外围事务0，执行回滚操作。
    // 运行结果：zhang shan 与 li shi 均未插入。
    @SuppressWarnings("Duplicates")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addRequiredException(user2);
    }

    //外围开启一个事务0，user1加入到这个事务0中，user2加入到这个事务0中，执行过程产生异常。
    //被外围事务0感知到，即使外围方法中try了，事务0已经在内部感知到了，简单说你的try catch时机不起作用。
    //运行结果：zhang shan 与 li shi 均未插入
    @SuppressWarnings("Duplicates")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception_try(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        try{
            user2Service.addRequiredException(user2);
        }
        catch (Exception e){

        }
    }


    //---------------------------

    //外围没有开启事务，user1Service开启一个新事务1，user2Service开启一个新事务2
    //外围方法产生异常不会影响，事务1，2。所以，zhang shan 与 li shi 都可以插入成功。
    @SuppressWarnings("Duplicates")
    public void notransaction_exception_requiresNew_requiresNew(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addRequiredNew(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addRequiredNew(user2);

        throw new RuntimeException();
    }

    //外围没有开启事务，user1Service开启一个新事务1，user2Service开启一个新事务2，user2Service出现异常。
    //事务2感知到这个异常。事务2回滚，事务1不受影响，事务1与事务2是两个独立的事务。
    //执行结果: zhang shan 插入成功，li shi 未插入。
    @SuppressWarnings("Duplicates")
    public void notranaction_requiresNew_requiresNew_exception(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addRequiredNew(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addRequiredNewException(user2);
    }

    //外围开启一个事务0，user1加入到事务0中，user2开启一个独立新事务2，user3开启一个独立的新事物3.
    //外围方法中产生一个异常，事务0感知到执行事务回滚。user1的数据被回滚掉了，但是，事务2，事务3都是独立的新事务。不受影响。
    //执行结果：zhang shan 未插入，li shi 插入。wang wu 插入。
    @SuppressWarnings("Duplicates")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_requiresNew_requireNew(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addRequiredNew(user2);

        User2 user3 = new User2();
        user3.setName("wang wu");
        user2Service.addRequiredNew(user3);
        throw new RuntimeException();
    }

    //外围开启一个事务0，user1加入到事务0中，user2开启一个独立新事务2，user3开启独立新事务3，但是user3执行过程中产生异常。
    //事务3感知到异常，事务3回滚，user3回滚掉了，异常抛到外围方法后。事务0感知到，所以是事务0也回滚了。user1被回滚掉了。但是，事务2是一个独立的新事务不受影响。
    //执行结果：zhang shan 未插入 li shi 插入 wang wu 未插入
    @SuppressWarnings("Duplicates")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addRequiredNew(user2);

        User2 user3 = new User2();
        user3.setName("wang wu");
        user2Service.addRequiredNewException(user3);
    }

    //外围开启一个新事务0，user1加入到事物0，user2开启一个独立的新事物1，user3开启一个独立的新事务2，执行产生了异常，事务2感知到进行了回滚，
    //user3 被回滚掉了，异常被外面try catch了，外围方法的事务0不会感知到。事务1是独立不受影响。
    //执行结果: zhang shan 插入，li shi 插入 ，wang wu 未插入
    @SuppressWarnings("Duplicates")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception_try(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addRequiredNew(user2);

        User2 user3 = new User2();
        user3.setName("wang wu");
        try{
            user2Service.addRequiredNewException(user3);
        }
        catch (Exception e) {
            System.out.println("回滚");
        }

    }


    //-------------------------------------------------------------------
    //外围没有事务，user1开启一个新事务0，由于外围没有事务，事务0是一个独立的新事务。user2一样开启了一个事务1
    //外围方法中产生了一个异常，事务0与事务1，都无法感知。
    //执行结果：zhang shan 插入 li shi 插入。
    @SuppressWarnings("Duplicates")
    public void notransaction_exception_nested_nested(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addNested(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addNested(user2);

        throw new RuntimeException();
    }

    //外围没有事务，user1开启一个新事务0，由于外围没有事务，事务0是一个独立的新事务。user2一样开启了一个事务1
    //user2在执行过程中产生异常，事务1感知到这个异常，执行回滚。user2被回滚掉了。异常抛到外围方法中。事务1不受影响
    //执行结果： zhang shan 插入，li shi 未插入。
    @SuppressWarnings("Duplicates")
    public void notransaction_nested_nested_exception(){
        User1 user1 = new User1();
        user1.setName("zhang shan");
        user1Service.addNested(user1);

        User2 user2 = new User2();
        user2.setName("li shi");
        user2Service.addNestedException(user2);
    }

    //外围开启一个事务0，user1开启一个事务0的子事务1，user2开启了一个事务0的子事务2。
    //外围方法产生了一个异常，事务0感知到回滚，所有子事务都要回滚。
    //zhang shan 未插入，li shi 未插入
    @SuppressWarnings("Duplicates")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_nested_nested(){
        User1 user1=new User1();
        user1.setName("zhang shan");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("li shi");
        user2Service.addNested(user2);
        throw new RuntimeException();
    }

    //外围开启一个事务0，user1开启了事务0的子事务1，user2开启了一个事务0的子事务2
    //user2方法中产生了一个异常，子事务2感知到，子事务2回滚。异常抛出到外围方法中，事务0感知到，回滚。其所有子事务都需要跟着回滚。
    //执行结果：zhang shan 未插入，li shi 未插入
    @SuppressWarnings("Duplicates")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_nested_nested_exception(){
        User1 user1=new User1();
        user1.setName("zhang shan");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("li shi");
        user2Service.addNestedException(user2);
    }


    // 外围开启一个事务0，user1开启了一个事务0的子事务1，user2开启了一个事务0的子事务2，user2方法执行过程中产生了一个异常，
    // 子事务2回滚，抛出异常到外围方法中，由于有try catch，外围事务0没有感知到这个异常。所以子事务1不受影响。
    // 执行结果 : zhang shan 插入，lishi 未插入
    @SuppressWarnings("Duplicates")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_nested_nested_exception_try(){
        User1 user1=new User1();
        user1.setName("zhang shan");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("li shi");
        try {
            user2Service.addNestedException(user2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }



}
