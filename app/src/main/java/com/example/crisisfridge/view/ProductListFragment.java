package com.example.crisisfridge.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crisisfridge.R;
import com.example.crisisfridge.model.InventoryItem;
import com.example.crisisfridge.model.InventoryItemI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductListFragment extends Fragment {

    private RecyclerView invItemRecyclerView;
    private InvItemAdapter invItemAdapter;
    private static final int REQUEST_EDIT = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        invItemRecyclerView = view.findViewById(R.id.crime_recycler_view);
        invItemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        ArrayList<InventoryItemI> inventoryItems = new ArrayList<>();
        inventoryItems.add(new InventoryItem("s",0.9f, new Date()));
        invItemAdapter = new InvItemAdapter(inventoryItems);
        invItemRecyclerView.setAdapter(invItemAdapter);
    }

    private class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private InventoryItemI inventoryItem;
        private TextView nameTextView;
        private TextView dateTextView;
        private TextView quantityTextView;

        public ProductHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);

            nameTextView = itemView.findViewById(R.id.item_name);
            quantityTextView = itemView.findViewById(R.id.item_quantity);
            dateTextView = itemView.findViewById(R.id.item_expiration_date);
        }

        public void bind(InventoryItemI inventoryItem) {
            this.inventoryItem = inventoryItem;
            nameTextView.setText(this.inventoryItem.getName());
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            Date date = this.inventoryItem.getDate();
            String dateStr = formatter.format(date);
            dateTextView.setText(dateStr);
            quantityTextView.setText(String.valueOf(inventoryItem.getQuantity()));
        }

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getFragmentManager();
            ProductEditFragment productEditFragment = ProductEditFragment.newInstance(
                    inventoryItem.getName(),
                    inventoryItem.getQuantity(),
                    inventoryItem.getDate()
            );
            productEditFragment.setTargetFragment(ProductListFragment.this, REQUEST_EDIT);
            productEditFragment.show(fragmentManager,"Edit Product");
        }
    }

    private class InvItemAdapter extends RecyclerView.Adapter<ProductHolder> {
        private List<InventoryItemI> inventoryItemList;

        public InvItemAdapter(List<InventoryItemI> inventoryItemList) {
            this.inventoryItemList = inventoryItemList;
        }

        @NonNull
        @Override
        public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view;
            int layout;

            layout = R.layout.list_item_product;

            view = layoutInflater.inflate(layout, parent, false);

            return new ProductHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
            InventoryItemI inventoryItem = inventoryItemList.get(position);
            holder.bind(inventoryItem);
        }

        @Override
        public int getItemCount() {
            return inventoryItemList.size();
        }

    }
}