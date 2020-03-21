package com.example.crisisfridge.view;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public abstract class ProductFragment extends DialogFragment {
    protected TextView inv_item_name;
    protected EditText inv_item_quantity;
    protected DatePicker inv_item_expiration_date;


    public static final String EXTRA_INV_ITEM_NAME =
            "com.example.crisisfridge.inv_item_name";
    public static final String EXTRA_INV_ITEM_QUANTITY =
            "com.example.crisisfridge.inv_item_quantity";
    public static final String EXTRA_INV_ITEM_EXP_DATE =
            "com.example.crisisfridge.inv_item_date";
    public static final String EXTRA_INV_ITEM_NUMBER =
            "com.example.crisisfridge.inv_item_number";


    protected static final String ARG_DATE = "date";
    protected static final String ARG_QUANTITY = "quantity";
    protected static final String ARG_NAME = "name";
    protected static final String ARG_NUMBER = "number";
}
