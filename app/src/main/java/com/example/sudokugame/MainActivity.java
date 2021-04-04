package com.example.sudokugame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "message";
    private static Integer Item;
    private static String itemName=null;
    private Intent intent4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate:");

        // intent to change to gamescreen to be used on user difficulty selection

       intent4 = new Intent(this, GameActivity.class);

        // making interactive buttons
        Button b_easy = findViewById(R.id.button_easy);



        b_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // selected puzzle number
                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                popup.setOnMenuItemClickListener(MainActivity.this);
                popup.inflate(R.menu.menu);
                popup.show();



            }
        });



    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this,"Selected Item: "+item.getTitle(),Toast.LENGTH_SHORT).show();
        itemName= (String) item.getTitle();
        intent4.putExtra("deneme",itemName);
        startActivity(intent4);
        switch (item.getItemId()){
            case R.id.kolay:
            return true;
            case R.id.orta:
                return true;
            case R.id.zor:
                return true;
            default:
                return false;
        }
    }
}
