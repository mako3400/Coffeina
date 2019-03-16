package com.example.coffeina;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {

    //Dodanie stałej
    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Pobieramy identyfikator napoju z intencji
        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINKNO);
        //Tworzymy kursor który pobiera z tabeli DRINK bazy danych wartości kolumn NANE,
        //DESCRIPTION oraz IMAGE_RESOURCE_ID wiersza, w którym wartość kolumny _id
        //odpowiada wartości zmiennej drinkNo
        try {
            SQLiteOpenHelper coffeinaDatabaseHelper = new CoffeinaDatabaseHelper(this);
            SQLiteDatabase db = coffeinaDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                    new String[]{"NAME","DESCRIPTION",
                    "IMAGE_RESOURCE_ID"},
                    "_id =?",
                    new String[]{Integer.toString(drinkNo)},
                    null,null,null);
            //Przechodzimy do pierwszego rekordu w kursorze
            if (cursor.moveToFirst()){
                //Pobieramy z kursora szczegółowe informacje o napoju
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                //Wyświetlamy nazwy napojów
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

               //Wyświetlamy opis napoju
                TextView description = (TextView)findViewById(R.id.description);
                description.setText(descriptionText);

                //Wyświetlamy zdjęcia napoju
                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }
            //Zamykamy kursor i bazę danych
            cursor.close();
            db.close();
            //Jeślizostanie zgłoszony wyjątek SQLiteException, bedzie on oznaczał problemy
            //z bazą danych. W takim przypadku używamy klasy Toast,
            // by wyświetlić użytkownikowi stosowny komunikat
        }catch (SQLException e){
            Toast toast = Toast.makeText(this, "Baza danych jest niedostępna", Toast.LENGTH_SHORT);
            toast.show();
        }





        //Użyj zmiennej drinkNo do pobrania szczegółowych informacji o napoju
        //kliknietym przez użytkownika
        //Drink drink = Drink.drinks[drinkNo];

        //Wyświetlamy zdjęcia napoju z intencji
        //ImageView photo = (ImageView)findViewById(R.id.photo);
        //photo.setImageResource(drink.getImageResourceId());
        //photo.setContentDescription(drink.getName());

        //Wyświetlamy nazwę napoju
        //TextView name = (TextView)findViewById(R.id.name);
        //name.setText(drink.getName());

        //Wyswietlamy opis napoju
        //TextView description =(TextView)findViewById(R.id.description);
        //description.setText(drink.getDescription());
    }
}
