package springbootmvc.exceptions;

public class MyException extends RuntimeException {

    private int errCode;

    public MyException(String msg,int errCode){
        super(msg);
        this.errCode = errCode;
    }

    @Override
    public String toString() {
        return getMessage() + " " + errCode;
    }
}
