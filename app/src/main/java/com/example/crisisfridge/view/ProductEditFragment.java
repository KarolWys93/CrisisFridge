package com.example.crisisfridge.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.crisisfridge.R;
import com.example.crisisfridge.model.InventoryItem;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProductEditFragment  extends DialogFragment {

    public static final String EXTRA_INV_ITEM_NAME =
      "com.example.crisisfridge.inv_item_name";
    public static final String EXTRA_INV_ITEM_QUANTITY =
      "com.example.crisisfridge.inv_item_quantity";
    public static final String EXTRA_INV_ITEM_DATE =
            "com.example.crisisfridge.inv_item_date";
    public static final String EXTRA_INV_ITEM_NUMBER =
            "com.example.crisisfridge.inv_item_number";


    private static final String ARG_DATE = "date";
    private static final String ARG_QUANTITY = "quantity";
    private static final String ARG_NAME = "name";
    private static final String ARG_NUMBER = "number";


    private EditText inv_item_name_edit;
    private EditText inv_item_quantity_edit;
    private DatePicker inv_item_expiration_date_edit;

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
        int number = (int) getArguments().getSerializable(ARG_NUMBER);

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
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = inv_item_expiration_date_edit.getYear();
                        int month =  inv_item_expiration_date_edit.getMonth();
                        int day = inv_item_expiration_date_edit.getDayOfMonth();
                        Date editDate = new GregorianCalendar(year,month,day).getTime();
                        float quantity = Float.parseFloat(inv_item_quantity_edit.getText().toString());
                        String name = inv_item_name_edit.getText().toString();
                        sendResult(Activity.RESULT_OK, new InventoryItem(name,quantity,editDate),number);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, InventoryItem inventoryItem, int number){
        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_INV_ITEM_NAME,inventoryItem.getName());
        intent.putExtra(EXTRA_INV_ITEM_DATE,inventoryItem.getDate());
        intent.putExtra(EXTRA_INV_ITEM_QUANTITY,inventoryItem.getQuantity());
        intent.putExtra(EXTRA_INV_ITEM_NUMBER,number);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode, intent);
    }

}
