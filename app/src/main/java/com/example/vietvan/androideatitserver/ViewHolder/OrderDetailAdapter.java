package com.example.vietvan.androideatitserver.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vietvan.androideatitserver.Model.Order;
import com.example.vietvan.androideatitserver.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by VietVan on 29/06/2018.
 */

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.DetailViewHolder>{

    List<Order> list;

    public OrderDetailAdapter(List<Order> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_layout, null);

        return new DetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{

        TextView name, quantity, price, discount;

        public DetailViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            quantity = itemView.findViewById(R.id.product_quantity);
            price = itemView.findViewById(R.id.product_price);
            discount = itemView.findViewById(R.id.product_discount);
        }

        public void setData(Order order){
            Locale locale = new Locale("en", "US");
            NumberFormat format = NumberFormat.getCurrencyInstance(locale);

            name.setText("Name: " + order.getProductName());
            quantity.setText("Quantity: " + order.getQuantity());
            price.setText("Price: " + format.format(Integer.parseInt(order.getPrice())));
            discount.setText("Discount: " + order.getDiscount());
        }

    }

}
