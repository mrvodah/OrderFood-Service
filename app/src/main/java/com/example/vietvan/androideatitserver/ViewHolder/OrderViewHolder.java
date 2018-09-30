package com.example.vietvan.androideatitserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.vietvan.androideatitserver.Common.Common;
import com.example.vietvan.androideatitserver.Interface.ItemClickListener;
import com.example.vietvan.androideatitserver.R;

import info.hoang8f.widget.FButton;

/**
 * Created by VietVan on 20/06/2018.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder{

    public TextView tvOrderId, tvOrderStatus, tvOrderPhone, tvOrderAddress;
    public FButton edit, details, remove, direction;

    public OrderViewHolder(View itemView) {
        super(itemView);

        tvOrderId = itemView.findViewById(R.id.order_id);
        tvOrderStatus = itemView.findViewById(R.id.order_status);
        tvOrderPhone = itemView.findViewById(R.id.order_phone);
        tvOrderAddress = itemView.findViewById(R.id.order_address);

        edit = itemView.findViewById(R.id.btnEdit);
        remove = itemView.findViewById(R.id.btnRemove);
        details = itemView.findViewById(R.id.btnDetails);
        direction = itemView.findViewById(R.id.btnDirection);
    }

}