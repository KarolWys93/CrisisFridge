package com.example.crisisfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import com.example.crisisfridge.view.ProductActivity;
import com.example.crisisfridge.view.ProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.food_list:
                    System.out.println("frytki");
                    startActivity(new Intent(getApplicationContext(), ProductActivity.class));
                    return true;
                case R.id.recipes:
                    System.out.println("ziemniaczki");
                    return true;
                case R.id.shopping_list:
                    System.out.println("pipsy");
                    return true;
            }
            return false;

        });

    }
}
