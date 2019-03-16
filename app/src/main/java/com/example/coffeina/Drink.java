package com.example.coffeina;

//Kazdy Drink ma nazwe i opis oraz zawiera identyfikator zasbu graficznego.
//Te identyfikatory zasobów okeslaja zdjęcia zasobów, które dodamy do projektu.
public class Drink {
    private String name;
    private  String description;
    private int imageResourceId;


    //drinks to tablica obiektów klasy Drink(trzech obiektów)
    public static  final  Drink[] drinks = {
            new Drink("Latte","Czarne espresso z gorącym mlekiem i mleczną czekoladą.",R.drawable.latte),
            new Drink("Cappuccino", "Czarne espresso z dużą ilością spienionego mleka.",R.drawable.cappuccino),
            new Drink("Espresso","Czarna kawa ze świeżo mielonym ziarnem najwyższej jakości.",R.drawable.filter)
    };

    //Konstruktor klasy Drink
    //Każdy drink ma nazwę , opis oraz zasób graficzny
    private Drink(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }
     // Metody get, akcesory do pobierania informacji ze zmiennych prywatnych.
    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
    //Łańcuchową reprezentującą obiektu Drink jest nazwa napoju
    public String toString(){
        return this.name;
    }
}
