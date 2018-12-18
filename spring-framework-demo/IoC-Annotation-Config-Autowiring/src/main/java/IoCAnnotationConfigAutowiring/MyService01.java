package IoCAnnotationConfigAutowiring;

public class MyService01 {

    public String getObjectInfo() {
        return String.format("----%s----%s", this, this.hashCode());
    }
}
