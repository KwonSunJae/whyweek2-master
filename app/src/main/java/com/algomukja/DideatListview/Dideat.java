package com.algomukja.DideatListview;

import java.util.ArrayList;

public class Dideat {
    private String date;

    private String img;
    private ArrayList<Food> foodArrayList;
    public Dideat(String date, String img, ArrayList<Food> l){
        this.date = date;
        this.img =img;
        this.foodArrayList =l;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArrayList<Food> getFoodArrayList() {
        return foodArrayList;
    }

    public void addFoodArrayList(Food food) {
        this.foodArrayList.add(food);
    }
    public  int lengthFoodArr(){
        return this.foodArrayList.size();
    }
}
