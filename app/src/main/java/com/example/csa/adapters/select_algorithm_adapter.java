package com.example.csa.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csa.Models.input_process_model;
import com.example.csa.R;

import java.util.List;

public class select_algorithm_adapter extends RecyclerView.Adapter<select_algorithm_adapter.viewholder> {
    Context context;
    String[] list;
    int selected_position=-1;

    public select_algorithm_adapter(Context context) {
        this.context = context;
        list=new String[]{"FIFO","HRRN","RR","SJF","SRT"};
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.algorithm_recycler_model, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {
        holder.name.setText(list[position]);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                holder.name.setTextColor(context.getResources().getColor(R.color.textwhite));
                selected_position=position;
            }
        });
    }


    public String which_is_selected()
    {
        if (selected_position!=-1)
            return list[selected_position];
        else
            Log.i("null", "which_is_selected: nuuuullllllllllll");
            return null;
    }

    @Override
    public int getItemCount() {
        return list.length;
    }


    class viewholder extends RecyclerView.ViewHolder {
        TextView name;
        CardView cardView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.algorithm_cardview);
            name=itemView.findViewById(R.id.select_algorithm_nametv);

        }
    }


}
