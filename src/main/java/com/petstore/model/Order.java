package com.petstore.model;

//You will need to create a Java Object. Jersey uses these to contruct requests and responses.

public class Order {
    private int order_id;
    private int user_id;
    private String pet_id;
    private int qty;
    private float price;
    private String name_first;
    private String name_last;
    private String email;
    private String address_zipcode;
    private String address_state;
    private String address;
    private String card_number;
    private String expiration_MM;
    private String expiration_YY;
    private String shipping_method;
    private boolean paid;
    private String phone_number;

    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getPet_id() { return pet_id; }
    public void setPet_id(String pet_id) { this.pet_id = pet_id; }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName_first() {
        return name_first;
    }

    public void setName_first(String name_first) {
        this.name_first = name_first;
    }

    public String getName_last() {
        return name_last;
    }

    public void setName_last(String name_last) {
        this.name_last = name_last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress_zipcode() {
        return address_zipcode;
    }

    public void setAddress_zipcode(String address_zipcode) {
        this.address_zipcode = address_zipcode;
    }

    public String getAddress_state() {
        return address_state;
    }

    public void setAddress_state(String address_state) {
        this.address_state = address_state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getExpiration_MM() {
        return expiration_MM;
    }

    public void setExpiration_MM(String expiration_MM) {
        this.expiration_MM = expiration_MM;
    }

    public String getExpiration_YY() {
        return expiration_YY;
    }

    public void setExpiration_YY(String expiration_YY) {
        this.expiration_YY = expiration_YY;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}