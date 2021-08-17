package com.example.t_guide;

import java.sql.Blob;

public class Review {
    byte[] img;
    String name;
    int rating;
    String date;
    String review;

    public Review(byte[] img, String name, int rating, String date, String review) {
        this.img = img;
        this.name = name;
        this.rating = rating;
        this.date = date;
        this.review = review;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
