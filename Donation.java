package com.example.beathunger1;

public class Donation {
    private String donorId;
    private String name;
    private String phone;
    private String address;
    private String foodName;
    private String foodQuantity;
    private String foodCategory;
    private String date;
    private String time;

    public Donation() {
    }

    public Donation(String donorId, String name, String phone, String address, String foodName, String foodQuantity, String foodCategory, String date, String time) {
        this.donorId = donorId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.foodName = foodName;
        this.foodQuantity = foodQuantity;
        this.foodCategory = foodCategory;
        this.date = date;
        this.time = time;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
