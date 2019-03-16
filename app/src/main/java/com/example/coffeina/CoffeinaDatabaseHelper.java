package com.example.coffeina;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Klasa pomocnicza SQLite musi dziedziczyć po klasie SQLiteOpenHelper
// Do tworzenia bazy danych

class CoffeinaDatabaseHelper extends SQLiteOpenHelper {

    private  static  final  String DB_NAME = "coffeina"; //Nazwa bazy danych
    private static  final  int DB_VERSION = 2;//Numer wersji bazy danych

    CoffeinaDatabaseHelper(Context context){
        //Wywołujemy konstruktor klasy bazowej SQLliteOpenHelper,
        //przekazując do niego nazwę i numer wersji bazy danych
        super(context, DB_NAME,null, DB_VERSION);

    }
    //Metoda onCreate() zostaje wywołana podczas pierwszego tworzenia bazy danych,
    //dlatego jej właśnie używamy do utworzenia tabeli bazy danych i
    //zapisania w niej początkowych danych
    @Override
    public void onCreate(SQLiteDatabase db) {
       updateMyDatabase(db,0,DB_VERSION);

    }
    //Metoda wywołyywana kiedy pojawi się potrzeba aktualizacji bazy danych
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db,oldVersion,newVersion);
    }
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Tworzymy tabele Drink
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE DRINK(_id INTEGER PRIMARY KEY AUTOINCREMENT,"

                    + "NAME TEXT,"
                    + "DESCTRIPTION TEXT,"
                    + "IMAGE_RESOURCE_ID INTEGER);");
            //Kazdy napój zapisujemy jako osobny wiersz tabeli
            insertDrink(db, "Latte", "Czarne espresso z gorącym mlekiem i mleczną czekoladą.", R.drawable.latte);
            insertDrink(db, "Cappuccino", "Czarne espresso z dużą ilością spienionego mleka.", R.drawable.cappuccino);
            insertDrink(db, "Espresso", "Czarna kawa ze świeżo mielonym ziarnem najwyższej jakości.", R.drawable.filter);
        }
        if (oldVersion < 2) {
            //Do tabeli Drink dodajemy kolumne Favorite typu liczbwego
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }
    //Metoda która zapisze w tabeli kilka napojów
    private static void insertDrink(SQLiteDatabase db, String neme,
                                    String description, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME",neme);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DRINK", null, drinkValues);

    }

}
