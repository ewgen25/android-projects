package com.example.yandexlavka;

import java.util.ArrayList;

public class Food {

    private final String name;
    private final double price;
    private int image;

    public Food(String name, double price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static ArrayList<Food> createFoodList() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("Хинкали с сыром", 35, R.drawable.hinkali));
        foods.add(new Food("Хинкали с бараниной", 45, R.drawable.hinkali));
        foods.add(new Food("Хинкали со свининой", 65, R.drawable.hinkali));
        foods.add(new Food("Хинкали с тыквой", 60, R.drawable.hinkali));
        foods.add(new Food("Хинкали 10шт", 350, R.drawable.hinkali));
        return foods;

    }
}
