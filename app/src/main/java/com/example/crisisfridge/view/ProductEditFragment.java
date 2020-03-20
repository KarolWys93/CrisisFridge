package com.example.crisisfridge.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.crisisfridge.R;

import java.util.Calendar;
import java.util.Date;

public class ProductEditFragment  extends DialogFragment {

    private static final String ARG_DATE = "date";
    private static final String ARG_QUANTITY = "quantity";
    private static final String ARG_NAME = "name";


    EditText inv_item_name_edit;
    EditText inv_item_quantity_edit;
    DatePicker inv_item_expiration_date_edit;

    public static  ProductEditFragment newInstance(String name, float quantity, Date date ){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        args.putSerializable(ARG_NAME, name);
        args.putSerializable(ARG_QUANTITY, quantity);

        ProductEditFragment fragment = new ProductEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_edit_product, null);
        inv_item_name_edit = v.findViewById(R.id.inv_item_name_edit);
        inv_item_quantity_edit = v.findViewById(R.id.inv_item_quantity_edit);
        inv_item_expiration_date_edit= v.findViewById(R.id.inv_item_expiration_date_edit);

        String name = (String) getArguments().getSerializable(ARG_NAME);
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        float quantity = (float) getArguments().getSerializable(ARG_QUANTITY);

        inv_item_name_edit.setText(name);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        inv_item_expiration_date_edit.init(year,month,day,null);
        inv_item_quantity_edit.setText(String.valueOf(quantity));

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.product_edit_title)
                .setView(v)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }


}
