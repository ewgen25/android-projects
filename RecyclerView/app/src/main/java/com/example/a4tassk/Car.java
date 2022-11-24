package com.example.a4tassk;

import java.util.ArrayList;

public class Car {
    private String carName; //переменная, хранящая название машины
    private double carRating; //переменная, хранящая рейтинг машины
    private String carCost; //переменная, хранящая стоимость аренды
    private String carColor; // переменная, хранящая цвет машины
    private String carClass; //переменная, хранящая класс машины
    private int carImg;
    private String carId;
    //конструктор
    public Car(String carName, double carRating, String carCost, String carColor, String carClass, int carImg, String carId) {

        this.carName = carName;
        this.carRating = carRating;
        this.carCost = carCost;
        this.carColor = carColor;
        this.carClass = carClass;
        this.carImg = carImg;
        this.carId = carId;

    }
    //специальные методы доступа
    public String getCarName() {
        return carName;
    }
    public void setCarName(String carName) {
        this.carName = carName;
    }
    public double getCarRating() {
        return carRating;
    }
    public void setCarRating(double carRating) {
        this.carRating = carRating;
    }
    public String getCarCost() {
        return carCost;
    }
    public void setCarCost(String carCost) {
        this.carCost = carCost;
    }
    public String getCarColor() {
        return carColor;
    }
    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }
    public String getCarClass() {
        return carClass;
    }
    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }
    public int getCarImg() {
        return carImg;
    }
    public void setCarImg(int carImg) {
        this.carImg = carImg;
    }
    public String getCarId() {
        return carId;
    }
    public void setCarId(String carId) {
        this.carId = carId;
    }


    public static ArrayList<Car> createCarList() {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Lamborghini Gallardo", 4.6, "32.000", "Желтый", "Элитный", R.drawable.id1, "id1"));
        cars.add(new Car("Porsche 981", 4.8, "20.000", "Белый", "Спорт", R.drawable.id2,"id2"));
        cars.add(new Car("Ford Mustang 5.3", 5.0, "18.000", "Черный", "Спорт", R.drawable.id3, "id3"));
        cars.add(new Car("Ferrari F42", 4.9, "44.000", "Белый", "Элитный", R.drawable.id4, "id4"));
        cars.add(new Car("BMW i8", 5.0, "18.000", "Красный", "Спорт", R.drawable.id5, "id5"));
        cars.add(new Car("Жигули V12", 1.0, "4.000", "Белый", "Спорт", R.drawable.id5, "id5"));

        return cars;
    }
}




