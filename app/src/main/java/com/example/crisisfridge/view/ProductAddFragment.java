package com.example.crisisfridge.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.crisisfridge.R;
import com.example.crisisfridge.model.InventoryItem;

public class ProductAddFragment extends Fragment {
    private InventoryItem inventoryItem;
    private EditText productNameField;
    private EditText productDateField;
    private EditText productQuantityField;
    private Button addProduct;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        inventoryItem = new InventoryItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        productNameField = view.findViewById(R.id.inv_item_name_edit);
        addProduct = view.findViewById(R.id.edit_inv_item);
        productDateField = view.findViewById(R.id.inv_item_expiration_date_edit);
        productQuantityField = view.findViewById(R.id.inv_item_quantity_edit);
        productNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventoryItem.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

    }
}
