package com.example.crisisfridge.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crisisfridge.R;
import com.example.crisisfridge.model.InventoryItem;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProductAddFragment extends ProductFragment {



    private EditText inv_item_name_edit;
    private EditText inv_item_quantity_edit;
    private DatePicker inv_item_expiration_date_edit;

    public static  ProductAddFragment newInstance (){
        ProductAddFragment fragment = new ProductAddFragment();
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_edit_product, null);
        inv_item_name_edit = v.findViewById(R.id.inv_item_name_edit);
        inv_item_quantity_edit = v.findViewById(R.id.inv_item_quantity_edit);
        inv_item_expiration_date_edit= v.findViewById(R.id.inv_item_expiration_date_edit);



        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.product_add_title)
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
                        sendResult(Activity.RESULT_OK, new InventoryItem(name,quantity,editDate));
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, InventoryItem inventoryItem){
        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_INV_ITEM_NAME,inventoryItem.getName());
        intent.putExtra(EXTRA_INV_ITEM_DATE,inventoryItem.getDate());
        intent.putExtra(EXTRA_INV_ITEM_QUANTITY,inventoryItem.getQuantity());

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode, intent);
    }
}
