package com.example.coffeina;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity extends ListActivity {
    //Prywatne zmienne bazy danych oraz kursora
    private SQLiteDatabase db;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listDrinks = getListView();

        try {
            SQLiteOpenHelper coffeinaDatabaseHelper = new CoffeinaDatabaseHelper(this);
            //Pobieramy referencję do bazy danych
            db = coffeinaDatabaseHelper.getReadableDatabase();
            //Tworzymy kursor
            cursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            //Tworzymy adapter SimpleCursorAdapter
            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    //Odwzorowujemy zawartośc kolumny NAME na widok tekstowy wyświetlanyw komponencie ListView
                    new int[]{android.R.id.text1},
                    0);
            //Adapter kursora typu SimpleCursorAdapter
            listDrinks.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            //Jeśli został zgłoszony wyjątek SQLiteExeption to wyświetlamy komunikat dla użytkownika
            Toast toast = Toast.makeText(this, "Baza danych jest niedostępna", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
   //Metoda zamyka kursor i baze danych.
   // Kursor pozostanie otwarty aż do momentu, gdy adapter nie będzie go potrzebował
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    //Fragment kodu wypełnia widok listy danymi pochodzącymi z tablicy drinks
        //ArrayAdapter<Drink> listAdappter = new ArrayAdapter<Drink>(
                //this, android.R.layout.simple_list_item_1,Drink.drinks);
        //listDrinks.setAdapter(listAdappter);}


    //Implementacja metody onListItemClick,tak aby kliknięcie któregoś z napojów
    //Wyświetlanych na liście spowodowało uruchomienie aktywności DrinkActivity
    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINKNO,(int) id);
        startActivity(intent);
    }
}
