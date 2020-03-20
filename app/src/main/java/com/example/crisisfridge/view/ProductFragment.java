package com.example.crisisfridge.view;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.util.Date;

public abstract class ProductFragment extends DialogFragment {
    protected EditText inv_item_name;
    protected EditText inv_item_quantity;
    protected DatePicker inv_item_expiration_date;

    public static  ProductEditFragment newInstance(String name, float quantity, Date date, int number ){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        args.putSerializable(ARG_NAME, name);
        args.putSerializable(ARG_QUANTITY, quantity);
        args.putSerializable(ARG_NUMBER, number);

        ProductEditFragment fragment = new ProductEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String EXTRA_INV_ITEM_NAME =
            "com.example.crisisfridge.inv_item_name";
    public static final String EXTRA_INV_ITEM_QUANTITY =
            "com.example.crisisfridge.inv_item_quantity";
    public static final String EXTRA_INV_ITEM_DATE =
            "com.example.crisisfridge.inv_item_date";
    public static final String EXTRA_INV_ITEM_NUMBER =
            "com.example.crisisfridge.inv_item_number";


    protected static final String ARG_DATE = "date";
    protected static final String ARG_QUANTITY = "quantity";
    protected static final String ARG_NAME = "name";
    protected static final String ARG_NUMBER = "number";
}
