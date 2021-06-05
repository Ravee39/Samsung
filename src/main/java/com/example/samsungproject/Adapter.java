package com.example.samsungproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<info> data;

    public Adapter(List<info> data){
        this.data = data;
    }

    public Adapter(){
    }

    public void setData(List<info> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         info Info = data.get(position);
         if (Info.a == true){
             holder.LL.setBackgroundColor(Color.parseColor("#D2E31212"));
        }
         else{
             holder.LL.setBackgroundColor(Color.parseColor("#7400FFC4"));
         }
         holder.SumR.setText(Info.Res);
         holder.DesR.setText(Info.Description);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout LL;
        private TextView DesR;
        private TextView SumR;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            LL = itemView.findViewById(R.id.ll);
            DesR = itemView.findViewById(R.id.DesR);
            SumR = itemView.findViewById(R.id.SumR);
        }
    }

}
