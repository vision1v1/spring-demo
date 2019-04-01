package usage;

import java.util.Date;

public class User {
    private String userId;
    private String userName;
    private String password;
    private Date createdTime;
    private Date updatedTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        stringBuilder.append(userId + " ");
        stringBuilder.append(userName + " ");
        stringBuilder.append(password + " ");
        stringBuilder.append(createdTime + " ");
        stringBuilder.append(updatedTime + " ");
        stringBuilder.append("]");
        return  stringBuilder.toString();
    }
}
