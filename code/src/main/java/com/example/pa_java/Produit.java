package com.example.pa_java;

public class Produit {
    public int id;
    public String type;

    public String name;
    public String brand_item;
    public String description;
    public double prix_buy;
    public double prix_sell;
    public String ref;
    public int point_lose;
    public int point_win;

    public Produit(int id, String type, String brand_item, String name, String description, double prix_buy, double prix_sell, String ref, int point_lose, int point_win) {
        this.id = id;
        this.type = type;
        this.brand_item = brand_item;
        this.name = name;
        this.description = description;
        this.prix_buy = prix_buy;
        this.prix_sell = prix_sell;
        this.ref = ref;
        this.point_lose = point_lose;
        this.point_win = point_win;
    }

    @Override
    public String toString() {
        return "Produit:\n"+"name : "+name+"\tbrand : "+brand_item + "\tref : "+ref + "\ntype : "+ type + "\ndescription : " +description + "\nPrix achat : " + prix_buy + "Є\t Prix vente : " + prix_sell + "Є\nNb achat point : " + point_lose + "\tNb point gagné : " + point_win;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand_item;
    }

    public void setBrand(String brand_item) {
        this.brand_item = brand_item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix_buy() {
        return prix_buy;
    }

    public void setPrix_buy(double prix_buy) {
        this.prix_buy = prix_buy;
    }

    public double getPrix_sell() {
        return prix_sell;
    }

    public void setPrix_sell(double prix_sell) {
        this.prix_sell = prix_sell;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getPoint_lose() {
        return point_lose;
    }

    public void setPoint_lose(int point_lose) {
        this.point_lose = point_lose;
    }

    public int getPoint_win() {
        return point_win;
    }

    public void setPoint_win(int point_win) {
        this.point_win = point_win;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
