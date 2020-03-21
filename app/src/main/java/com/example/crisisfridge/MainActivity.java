package com.example.crisisfridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentsSetup();

        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.food_list:
                    System.out.println("frytki");
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

    public void fragmentsSetup(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment==null){
            fragment = new RecipiesFragment();
            fragmentManager.beginTransaction().add(R.id.fragmentContainer,fragment).commit();
        }

    }
}
