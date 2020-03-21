package com.example.crisisfridge.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.crisisfridge.R;
import com.example.crisisfridge.data.model.dataModel.ProductType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ProductAddFragment extends ProductFragment {



    private Spinner inv_item_name_spinner;
    private EditText inv_item_quantity_edit;
    private DatePicker inv_item_expiration_date_edit;
    private static List<ProductType> productTypes;


    public static  ProductAddFragment newInstance (List<ProductType> productTypeList){
        ProductAddFragment fragment = new ProductAddFragment();
        productTypes = productTypeList;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_product, null);
        inv_item_name_spinner = v.findViewById(R.id.product_type_spinner);
        inv_item_quantity_edit = v.findViewById(R.id.inv_item_quantity_add);
        inv_item_expiration_date_edit= v.findViewById(R.id.inv_item_expiration_date_add);





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
                        LocalDate localDate = editDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        float quantity = Float.parseFloat(inv_item_quantity_edit.getText().toString());
                        String name = inv_item_name_edit.getText().toString();


                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_INV_ITEM_NAME, name);
                        intent.putExtra(EXTRA_INV_ITEM_EXP_DATE, localDate );
                        intent.putExtra(EXTRA_INV_ITEM_QUANTITY,quantity);

                        sendResult(Activity.RESULT_OK, intent);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, Intent intent){
        if (getTargetFragment() == null){
            return;
        }


        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode, intent);
    }
}
