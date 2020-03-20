package com.example.crisisfridge.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.crisisfridge.R;
import com.example.crisisfridge.model.InventoryItem;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProductEditFragment  extends ProductFragment {

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
        inv_item_name = v.findViewById(R.id.inv_item_name_edit);
        inv_item_quantity = v.findViewById(R.id.inv_item_quantity_edit);
        inv_item_expiration_date = v.findViewById(R.id.inv_item_expiration_date_edit);

        String name = (String) getArguments().getSerializable(ARG_NAME);
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        float quantity = (float) getArguments().getSerializable(ARG_QUANTITY);
        int number = (int) getArguments().getSerializable(ARG_NUMBER);

        inv_item_name.setText(name);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        inv_item_expiration_date.init(year,month,day,null);
        inv_item_quantity.setText(String.valueOf(quantity));

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.product_edit_title)
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = inv_item_expiration_date.getYear();
                        int month =  inv_item_expiration_date.getMonth();
                        int day = inv_item_expiration_date.getDayOfMonth();
                        Date editDate = new GregorianCalendar(year,month,day).getTime();
                        float quantity = Float.parseFloat(inv_item_quantity.getText().toString());
                        String name = inv_item_name.getText().toString();
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
