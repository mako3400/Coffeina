package com.example.coffeina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        //Tworzymy obiekt nasłuchujacy OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            //Implementacja metody onItemClick
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Jeśli użytkownik kliknie element Napoje, to uruchomiany aktywność.
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };
  //Dodajemy obiekt nasłuchujący do widoku listy
        ListView listView =(ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);

    }
}