package com.example.crisisfridge.view;



import androidx.fragment.app.Fragment;

public class ProductActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ProductListFragment();
    }
}
