package com.example.crisisfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.crisisfridge.data.database.entity.ProductTypeEntity;
import com.example.crisisfridge.data.model.api.IFridgeItemRepository;
import com.example.crisisfridge.data.model.api.IProductTypeRepository;
import com.example.crisisfridge.data.model.api.IRepositoryFactory;
import com.example.crisisfridge.data.model.api.RepositoryFactory;
import com.example.crisisfridge.data.model.dataModel.FridgeItem;
import com.example.crisisfridge.data.model.dataModel.ProductType;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;


    IFridgeItemRepository fridgeItemRepository;
    IProductTypeRepository productTypeRepository;

    private final String TAG = "POTATO";
    private List<FridgeItem> fridgeItemsList = new ArrayList<>();

    int potatoNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testCode();

        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.food_list:
                    System.out.println("frytki");
                    return true;
                case R.id.recipes:
                    System.out.println("ziemniaczki");
                    testCodeExec(potatoNum);
                    if (potatoNum > 2) potatoNum = 0;
                    return true;
                case R.id.shopping_list:
                    System.out.println("pipsy");
                    return true;
            }
            return false;

        });

    }

    private void testCode() {
        IRepositoryFactory repoFactory = new RepositoryFactory(this);
        productTypeRepository = repoFactory.getProductTypeRepository();
        fridgeItemRepository = repoFactory.getFridgeItemRepository();
        fridgeItemRepository.getFridgeItemList().observe(this, fridgeItems -> {
            StringBuilder builder = new StringBuilder();
            for (FridgeItem item : fridgeItems) {
                builder.append(item.getId())
                        .append("\r\n")
                        .append(" ")
                        .append(item.getQuantity())
                        .append(" ")
                        .append(item.getExpirationDate())
                        .append(" ")
                        .append(item.getName());
                Log.i(TAG, builder.toString());
            }
            fridgeItemsList.clear();
            fridgeItemsList.addAll(fridgeItems);
        });

    }

    private void testCodeExec(int i) {
        switch (i) {
            case 0:
                ProductType pt = new ProductTypeEntity(2, "bekon");
                fridgeItemRepository.addNewItemToFridge(pt, 100, LocalDate.now().plusDays(5));
                break;
            case 1:
                fridgeItemRepository.removeItemFromFridge(fridgeItemsList.get(1));
                break;
            case 2:
                FridgeItem fridgeItem = fridgeItemsList.get(0);
                fridgeItem.setQuantity(13);
                fridgeItemRepository.editItemFromFridge(fridgeItem);
        }
    }

}
