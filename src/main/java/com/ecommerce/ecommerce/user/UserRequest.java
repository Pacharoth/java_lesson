package com.ecommerce.ecommerce.user;


public class UserRequest {
    private String name;
    private EnumUser gender;

    public UserRequest() {
    }
    public UserRequest(String name, EnumUser gender) {
        this.name = name;
        this.gender = gender;
    }
    public EnumUser getGender() {
        return gender;
    }
    public void setGender(EnumUser gender) {
        this.gender = gender;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
  
}
