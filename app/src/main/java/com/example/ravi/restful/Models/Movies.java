package com.example.ravi.restful.Models;

/**
 * Created by Ravi on 17-11-2016.
 */

public class Movies
{
    String titlte,image;
    int year;
    double rating;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    String genre;

    public String getTitlte() {
        return titlte;
    }

    public String setTitlte(String titlte) {
        this.titlte = titlte;
        return  titlte;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
