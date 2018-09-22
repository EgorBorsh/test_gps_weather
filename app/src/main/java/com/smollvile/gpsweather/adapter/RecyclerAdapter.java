package com.smollvile.gpsweather.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smollvile.gpsweather.R;
import com.smollvile.gpsweather.dto.ItemDTO;
import com.smollvile.gpsweather.listener.RecyclerItemListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    Context context;
    List<ItemDTO> list = new ArrayList<>();
    RecyclerItemListener listener;

    public RecyclerAdapter(Context context, List<ItemDTO> list, RecyclerItemListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        final RecyclerViewHolder holder = new RecyclerViewHolder(result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вызываем наш слушатель
                listener.onClikItem(v, getItem(holder.getPosition()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.cityCard.setText(list.get(position).getCity());
        String corStr = "( " + list.get(position).getCoordinates() + " )";
        holder.corCard.setText(corStr);
        holder.dateCard.setText(list.get(position).getDate());
        holder.timeCard.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView cityCard, corCard, dateCard, timeCard;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            cardView =(CardView) itemView.findViewById(R.id.card);
            cityCard =(TextView) itemView.findViewById(R.id.cityCard);
            corCard =(TextView) itemView.findViewById(R.id.corCard);
            dateCard =(TextView) itemView.findViewById(R.id.dateCard);
            timeCard =(TextView) itemView.findViewById(R.id.timeCard);
        }
    }

    public ItemDTO getItem(int position){
        return list.get(position);
    }
}
