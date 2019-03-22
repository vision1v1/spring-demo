package springbootmvc.response;

import com.fasterxml.jackson.annotation.JsonView;

public class User {

    public static class Public {
    }

    public static class Internal extends Public {
    }


    public User(int id, String itemName, String ownerName) {
        this.id = id;
        this.itemName = itemName;
        this.ownerName = ownerName;
    }

    @JsonView(User.Public.class)

    public int id;

    @JsonView(User.Public.class)
    public String itemName;

    @JsonView(User.Internal.class)
    public String ownerName;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
