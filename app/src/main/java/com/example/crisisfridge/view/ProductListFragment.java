package com.example.crisisfridge.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crisisfridge.R;
import com.example.crisisfridge.data.model.DatabaseMock;
import com.example.crisisfridge.data.model.api.IFridgeItemRepository;
import com.example.crisisfridge.data.model.api.IProductTypeRepository;
import com.example.crisisfridge.data.model.api.IRepositoryFactory;
import com.example.crisisfridge.data.model.api.RepositoryFactory;
import com.example.crisisfridge.data.model.dataModel.FridgeItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private RecyclerView invItemRecyclerView;
    private FloatingActionButton addInvItemButton;
    private InvItemAdapter invItemAdapter;
    private static final int REQUEST_INV_ITEM_EDIT = 0;
    private static final int REQUEST_INV_ITEM_ADD = 1;
    private List<FridgeItem> fridgeItems = new ArrayList<>();
    IRepositoryFactory repoFactory;
    IFridgeItemRepository fridgeRepo;
    IProductTypeRepository productTypeRepo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        invItemRecyclerView = view.findViewById(R.id.crime_recycler_view);
        invItemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addInvItemButton = view.findViewById(R.id.addInvItem);
        addInvItemButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();
            ProductAddFragment productAddFragment = ProductAddFragment.newInstance();
            productAddFragment.setTargetFragment(ProductListFragment.this, REQUEST_INV_ITEM_ADD);
            productAddFragment.show(fragmentManager,"Add Product");
        });

        repoFactory = new RepositoryFactory(DatabaseMock.getInstance());
        fridgeRepo = repoFactory.getFridgeItemRepository();
        productTypeRepo = repoFactory.getProductTypeRepository();
        updateUI();

        return view;
    }

    private void updateUI() {
        fridgeItems = fridgeRepo.getFridgeItemList();
        invItemAdapter = new InvItemAdapter(fridgeItems);
        invItemRecyclerView.setAdapter(invItemAdapter);
        invItemAdapter.notifyDataSetChanged();
    }

    private class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private FridgeItem inventoryItem;
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

        public void bind(FridgeItem inventoryItem) {
            this.inventoryItem = inventoryItem;
            nameTextView.setText(this.inventoryItem.getName());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            LocalDate localDate = this.inventoryItem.getExpirationDate();
            String dateStr = localDate.format(formatter);
            dateTextView.setText(dateStr);
            quantityTextView.setText(String.valueOf(inventoryItem.getQuantity()));
        }


        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getFragmentManager();
            ProductEditFragment productEditFragment = ProductEditFragment.newInstance(
                    inventoryItem.getName(),
                    inventoryItem.getQuantity(),
                    inventoryItem.getExpirationDate(),
                    getAdapterPosition()
            );

            productEditFragment.setTargetFragment(ProductListFragment.this, REQUEST_INV_ITEM_EDIT);
            productEditFragment.show(fragmentManager,"Edit Product");
        }


    }

    private class InvItemAdapter extends RecyclerView.Adapter<ProductHolder> {
        private List<com.example.crisisfridge.data.model.dataModel.FridgeItem> inventoryItemList;

        public InvItemAdapter(List<com.example.crisisfridge.data.model.dataModel.FridgeItem> inventoryItemList) {
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
            FridgeItem inventoryItem = inventoryItemList.get(position);
            holder.bind(inventoryItem);
        }




        @Override
        public int getItemCount() {
            return inventoryItemList.size();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        int number = extractInvItemNumberFromIntent(data);
        String name = extractInvItemNameFromIntent(data);
        float quantity = extractInvItemQuantityFromIntent(data);
        LocalDate localDate = extractInvItemExpDateFromIntent(data);

        switch (requestCode){
            case REQUEST_INV_ITEM_EDIT:
                FridgeItem fridgeItem = fridgeItems.get(number);
                fridgeItem.setExpirationDate(localDate);
                fridgeItem.setQuantity(quantity);
                fridgeRepo.editItemFromFridge(fridgeItem);
                break;
            case REQUEST_INV_ITEM_ADD:

                fridgeRepo.addNewItemToFridge();
                productTypeRepo.getProductTypeById();

                break;
            default:
        }
        updateUI();
    }

    private String extractInvItemNameFromIntent(Intent data){
        String name = (String) data.getSerializableExtra(
                ProductEditFragment.EXTRA_INV_ITEM_NAME);
        return name;
    }

    private float extractInvItemQuantityFromIntent(Intent data){
        float quantity = (float) data.getSerializableExtra(
                ProductEditFragment.EXTRA_INV_ITEM_QUANTITY);
        return quantity;
    }

    private int extractInvItemNumberFromIntent(Intent data){
        int number = (int) data.getSerializableExtra(
                ProductEditFragment.EXTRA_INV_ITEM_NUMBER);
        return number;
    }

    private LocalDate extractInvItemExpDateFromIntent(Intent data){
        LocalDate date = (LocalDate) data.getSerializableExtra(
                ProductEditFragment.EXTRA_INV_ITEM_EXP_DATE);
        return date;
    }


}
