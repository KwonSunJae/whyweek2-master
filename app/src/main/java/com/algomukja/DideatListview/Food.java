package com.algomukja.DideatListview;

public class Food {
    private int url;
    private int protein;
    private int fat;
    private int tansu;
    private int Nat;

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    private String fName;
    public Food(int t, int p, int f,int n, String name,int url){
        this.tansu =t;
        this.protein =p;
        this.fat =f;
        this.fName = name;
        this.Nat = n;
        this.url  = url;
    }
    public int getNat(){return  this.Nat;}
    public void setNat(int n){this.Nat=n;}
    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getTansu() {
        return tansu;
    }

    public void setTansu(int tansu) {
        this.tansu = tansu;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
}
