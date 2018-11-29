package com.example.microserviceschool.sao;

public class User {

    private Integer userId;

    private String name;

    private Integer age;

    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getUserId() == ((User)obj).getUserId();
    }

    @Override
    public int hashCode(){
        return getUserId().hashCode();
    }
}
