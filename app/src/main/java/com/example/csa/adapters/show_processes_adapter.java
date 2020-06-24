package com.example.csa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csa.Models.input_process_model;
import com.example.csa.R;
import java.util.List;

public class show_processes_adapter extends RecyclerView.Adapter<show_processes_adapter.viewholder> {
    Context context;
    List<input_process_model> list;
    int itemwidth=0;
    public show_processes_adapter(Context context, List<input_process_model> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.process_recycler_model, parent, false);
        return new viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.at_tv.setText(String.valueOf(list.get(position).getArrival_time()));
        holder.bt_tv.setText(String.valueOf(list.get(position).getCbt()));
        holder.complete.setText(String.valueOf(list.get(position).getCompleted()));
        if (holder.complete.getText().equals("-1"))
        {
            holder.complete.setVisibility(View.GONE);
            holder.text_complete.setVisibility(View.GONE);
        }
        if (position==0)
        itemwidth= holder.itemView.getWidth();
    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    class viewholder extends RecyclerView.ViewHolder {
        TextView at_tv,bt_tv,name,complete,text_complete;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            at_tv=itemView.findViewById(R.id.process_tv_at);
            bt_tv=itemView.findViewById(R.id.process_tv_bt);
            name=itemView.findViewById(R.id.process_tv_name);
            complete=itemView.findViewById(R.id.process_tv_complete);
            text_complete=itemView.findViewById(R.id.text_complete);
        }
    }

    public int getitemwidth()
    {
        return itemwidth;
    }

}
