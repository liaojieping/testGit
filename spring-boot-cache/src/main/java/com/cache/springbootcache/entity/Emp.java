package com.cache.springbootcache.entity;

public class Emp {
    private int id;
    private String userName;
    private String email;
    private String love;


    public Emp(){
        super();
    }
    public  Emp(int id,String userName,String email,String love){
        this.email=email;
        this.id=id;
        this.love=love;
        this.userName=userName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }
}
