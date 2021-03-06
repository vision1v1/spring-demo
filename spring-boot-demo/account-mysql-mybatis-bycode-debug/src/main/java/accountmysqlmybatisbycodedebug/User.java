package accountmysqlmybatisbycodedebug;

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
        StringBuilder content = new StringBuilder();
        content.append("[ ");
        content.append(userId + ", ");
        content.append(userName + ", ");
        content.append(password + ", ");
        content.append(createdTime + ", ");
        content.append(updatedTime);
        content.append(" ]");
        return content.toString();
    }
}