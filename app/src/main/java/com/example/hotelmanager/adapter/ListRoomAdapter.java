package com.example.hotelmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanager.databinding.RowItemListRoomBinding;
import com.example.hotelmanager.listener.IRecyclerView;
import com.example.hotelmanager.model.Room;

import java.text.DecimalFormat;
import java.util.List;

public class ListRoomAdapter extends RecyclerView.Adapter<ListRoomAdapter.ViewHolder> {

    List<Room> mList;
    Context context;
    private IRecyclerView listener;


    public ListRoomAdapter(List<Room> mList, Context context,IRecyclerView listener) {
        this.mList = mList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListRoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       RowItemListRoomBinding binding = RowItemListRoomBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
       return new ViewHolder(binding,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRoomAdapter.ViewHolder holder, int position) {
        Room room = mList.get(position);
        holder.binding.tvNameRoom.setText(room.getNameRoom());
        DecimalFormat formatPrice = new DecimalFormat("#,###");
        Double price = Double.parseDouble(room.getPriceRoom());
        holder.binding.tvPriceRoom.setText(new StringBuffer("Giá: ")
                .append(formatPrice.format(price))
                .append(" VND"));
        holder.binding.tvLoaiRoom.setText(room.getRoomType());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        RowItemListRoomBinding binding;

        private IRecyclerView listener;

        public ViewHolder(@NonNull RowItemListRoomBinding binding, IRecyclerView listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;

            itemView.setOnClickListener(v->{
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(mList.get(position));
                    }
                }

            });

        }
    }
}
