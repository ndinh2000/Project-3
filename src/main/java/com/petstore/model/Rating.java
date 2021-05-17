package com.petstore.model;

//You will need to create a Java Object. Jersey uses these to contruct requests and responses.

public class Rating {
    private int user_id;
    private String pet_id;
    private int rating;

    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getPet_id() {
        return pet_id;
    }
    public void setPet_id(String pet_id) { this.pet_id = pet_id; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}