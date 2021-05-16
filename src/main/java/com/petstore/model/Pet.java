package com.petstore.model;

import javax.xml.bind.annotation.XmlRootElement;

//You will need to create a Java Object. Jersey uses these to contruct requests and responses.

public class Pet {
    private String pet_id;
    private String name;
    private int age;
//    private int gender;
    private String gender;
    private double price;
    private String message;
    private String profile_picture;

    public String getPet_id() {
        return pet_id;
    }
    public String getName() {
        return name;
    }
    public int getAge() { return age; }
//    public String getGender() { return gender == 0 ? "Male" : "Female"; }
//    public int getGender() { return gender; }
    public String getGender() { return gender; }
    public double getPrice() { return price; }
    public String getMessage() { return message; }
    public String getProfile_picture() { return profile_picture; }

    public void setPet_id(String id) { this.pet_id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
//    public void setGender(int gender) { this.gender = gender; }
    public void setGender(String gender) { this.gender = gender; }
    public void setPrice(double price) { this.price = price; }
    public void setMessage(String message) { this.message = message; }
    public void setProfile_picture(String profile_picture) { this.profile_picture = profile_picture; }
}