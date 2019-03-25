package scanningandcomponents.beans;

public class HotGirl {

    private HotGirl nextGirl;

    private String name;

    public HotGirl(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    public HotGirl getNextGirl() {
        return nextGirl;
    }

    public void setNextGirl(HotGirl nextGirl) {
        this.nextGirl = nextGirl;
    }

    @Override
    public String toString() {
        if(nextGirl != null){
            return "HotGirl{" +
                    "nextGirl=" + nextGirl.toString() +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
        else{
            return "HotGirl{" +
                    "nextGirl= null" +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
