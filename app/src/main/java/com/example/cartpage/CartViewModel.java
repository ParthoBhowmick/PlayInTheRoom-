package com.example.cartpage;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartViewModel extends AndroidViewModel {
    private CartRepository repository;
    private LiveData<List<Cart>> allCarts;

    public CartViewModel(@NonNull Application application) {
        super(application);
        repository = new CartRepository(application);
        allCarts = repository.getAllCarts();
    }

    public void insert(Cart miniCart) {
        repository.insert(miniCart);
    }

    public void update(Cart miniCart,Context ctx) {
        repository.update(miniCart,ctx);
    }

    public void delete(Cart miniCart) {
        repository.delete(miniCart);
    }

    public void deleteAllCarts() {
        repository.deleteAllCarts();
    }

    public LiveData<List<Cart>> getAllCarts() {
        return allCarts;
    }

    public void findProductnAction(String productSku, Cart cart, Context context) {
        repository.findProductnAction(productSku,cart,context);
    }

}