package springaopusage;

public class SimplePojo implements Pojo {
    @Override
    public void foo() {
        // this next method invocation is a direct call on the 'this' reference
        this.bar();
    }

    @Override
    public void bar() {
        System.out.println("this : " + this);
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
