package com.example.vietvan.androideatitserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.vietvan.androideatitserver.Common.Common;
import com.example.vietvan.androideatitserver.ViewHolder.OrderDetailAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetail extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.order_phone)
    TextView orderPhone;
    @BindView(R.id.order_total)
    TextView orderTotal;
    @BindView(R.id.order_address)
    TextView orderAddress;
    @BindView(R.id.rv_orderDetail)
    RecyclerView rvOrderDetail;
    @BindView(R.id.order_comments)
    TextView orderComments;

    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);

        if(getIntent() != null){
            id = getIntent().getStringExtra("OrderId");
        }

        orderId.setText("#" + id);
        orderPhone.setText(Common.currentRequest.getPhone());
        orderAddress.setText(Common.currentRequest.getAddress());
        orderTotal.setText(Common.currentRequest.getTotal());
        orderComments.setText(Common.currentRequest.getComment());

        rvOrderDetail.setHasFixedSize(true);
        rvOrderDetail.setLayoutManager(new LinearLayoutManager(this));
        rvOrderDetail.setAdapter(new OrderDetailAdapter(Common.currentRequest.getList()));

    }
}
