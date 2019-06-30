package com.example.cartpage;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private CartRepository repository;
    private LiveData<List<Cart>> allCarts;

    public CartViewModel(@NonNull Application application) {
        super(application);
        repository = new CartRepository(application);
        allCarts = repository.getAllCarts();
    }

    public void insert(Cart note) {
        repository.insert(note);
    }

    public void update(Cart note) {
        repository.update(note);
    }

    public void delete(Cart note) {
        repository.delete(note);
    }

    public void deleteAllCarts() {
        repository.deleteAllCarts();
    }

    public LiveData<List<Cart>> getAllCarts() {
        return allCarts;
    }

    public Cart findProductnAction(String productName) {
        return repository.findProductnAction(productName);
    }

}