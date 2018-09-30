package com.example.vietvan.androideatitserver;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietvan.androideatitserver.Common.Common;
import com.example.vietvan.androideatitserver.Interface.ItemClickListener;
import com.example.vietvan.androideatitserver.Model.Request;
import com.example.vietvan.androideatitserver.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderStatus extends AppCompatActivity {

    @BindView(R.id.rv_order)
    RecyclerView rvOrder;

    FirebaseDatabase database;
    DatabaseReference requests;

    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    MaterialSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        rvOrder.setHasFixedSize(true);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));

        loadOrders();
    }

    private void loadOrders() {
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requests
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, final Request model, final int position) {
                viewHolder.tvOrderId.setText("#" + adapter.getRef(position).getKey());
                viewHolder.tvOrderAddress.setText(model.getAddress());
                viewHolder.tvOrderPhone.setText(model.getPhone());
                viewHolder.tvOrderStatus.setText(Common.convertCodeToStatus(model.getStatus()));

                viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showUpdate(adapter.getRef(position).getKey(), adapter.getItem(position));
                    }
                });

                viewHolder.remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteOrder(adapter.getRef(position).getKey());
                    }
                });

                viewHolder.details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Common.currentRequest = model;

                        Intent orderDetail = new Intent(OrderStatus.this, OrderDetail.class);
                        orderDetail.putExtra("OrderId", adapter.getRef(position).getKey());
                        startActivity(orderDetail);
                    }
                });

                viewHolder.direction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Common.currentRequest = model;
//
//                        Intent trackingOrder = new Intent(OrderStatus.this, TrackingOrder.class);
//                        startActivity(trackingOrder);
                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        rvOrder.setAdapter(adapter);
    }

    private void showUpdate(final String key, final Request item) {
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.update_order_layout, null);

        spinner = v.findViewById(R.id.spstatus);
        spinner.setItems("Placed", "On my way", "Shipped");

        new AlertDialog.Builder(this)
                .setTitle("Update OrderDetailAdapter")
                .setMessage("Please choose status?")
                .setView(v)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        item.setStatus(String.valueOf(spinner.getSelectedIndex()));
                        requests.child(key).setValue(item);

                        adapter.notifyDataSetChanged(); // add to update item size
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void deleteOrder(String key) {
        requests.child(key).removeValue();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Item Delete!", Toast.LENGTH_SHORT).show();
    }
}
