package com.example.cartpage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartPage extends AppCompatActivity {

    private CartViewModel cartViewModel;
    final CartAdapter adapter = new CartAdapter(this);
    TextView cartTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);

        cartTotal = findViewById(R.id.gotoCart);
        RecyclerView recyclerView = findViewById(R.id.cartList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        recyclerView.setAdapter(adapter);

        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        cartViewModel.getAllCarts().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(@Nullable List<Cart> carts) {
                adapter.setCarts(carts);
                double total = 0.0;
                for (Cart cart: carts) {
                    total = cart.getProduct_price()*cart.getQunatity() + total;
                }
                cartTotal.setText("Total: "+total);
            }
        });

    }

    public void deleteItem(Cart cart) {
        cartViewModel.delete(cart);
    }

    public void updateCartItem(Cart cart, Context ctx) {
        cartViewModel.update(cart,ctx);
    }

}
