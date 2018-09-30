package com.example.vietvan.androideatitserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vietvan.androideatitserver.Common.Common;
import com.example.vietvan.androideatitserver.Interface.ItemClickListener;
import com.example.vietvan.androideatitserver.R;

/**
 * Created by VietVan on 20/06/2018.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder implements
        View.OnCreateContextMenuListener{

    public static ImageView food_image;
    public static TextView food_name;

    public FoodViewHolder(View itemView) {
        super(itemView);

        food_image = itemView.findViewById(R.id.food_image);
        food_name = itemView.findViewById(R.id.food_name);

        itemView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select Action!");

        menu.add(0,0,getAdapterPosition(), Common.UPDATE);
        menu.add(0,1,getAdapterPosition(), Common.DELETE);
    }
}
