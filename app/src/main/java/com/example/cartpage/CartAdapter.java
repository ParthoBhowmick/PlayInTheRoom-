package com.example.cartpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    private List<Cart> items = new ArrayList<>();
    private Context mcontext;


    public CartAdapter(Context context) {
        this.mcontext = context;
    }

    public void setCarts(List<Cart> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_single_element, parent, false);
        return new CartHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        Cart currentitem = items.get(position);

        Glide.with(mcontext)
                .load(currentitem.getDisplayImg())
                .into(holder.cartImg);

        holder.productName.setText(currentitem.getProduct_name());
        holder.productPrice.setText("৳"+currentitem.getProuct_price()+"");
        holder.productSku.setText(currentitem.getSku());
        holder.productVariation.setText(currentitem.getVaiants());
        holder.qtyEt.setText(currentitem.getQunatity()+"");
        holder.stockCount.setText(currentitem.getStock() + " products available");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class CartHolder extends RecyclerView.ViewHolder {
        private ImageView cartImg,dltFrmCart;
        private CheckBox select;
        private ToggleButton wishButton;
        private TextView productName,productVariation,productSku,productPrice,stockCount;
        private Button plusBtn,minusBtn;
        private EditText qtyEt;


        public CartHolder(View itemView) {
            super(itemView);
            cartImg = itemView.findViewById(R.id.cart_singleImg);
            dltFrmCart = itemView.findViewById(R.id.deletFromCartImg);
            select = itemView.findViewById(R.id.select);
            wishButton = itemView.findViewById(R.id.wishButton);
            productName = itemView.findViewById(R.id.productName);
            productVariation = itemView.findViewById(R.id.variations);
            productSku = itemView.findViewById(R.id.productSku);
            productPrice = itemView.findViewById(R.id.productPrice);
            stockCount = itemView.findViewById(R.id.stockAvail);
            plusBtn = itemView.findViewById(R.id.plusBtn);
            minusBtn = itemView.findViewById(R.id.minusBtn);
            qtyEt = itemView.findViewById(R.id.qtyEditext);
        }
    }
}