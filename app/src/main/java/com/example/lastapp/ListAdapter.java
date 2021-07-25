package com.example.lastapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastapp.data.model.EventDataResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    Context context;
    List<EventDataResponse> list;

    public ListAdapter(Context ct, List<EventDataResponse> l){
        context = ct;
        list = l;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.event_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
        holder.textViewCategory.setText(list.get(position).getCategory());
        holder.textViewDateTime.setText("Date: " + list.get(position).getDate() +
                ", Starting time: " + list.get(position).getStartingTime() + ", Duration: " + list.get(position).getDuration());
        holder.textViewDescription.setText(list.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        TextView textViewCategory;
        TextView textViewDateTime;
        TextView textViewDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.eventRow);
            textViewCategory = itemView.findViewById(R.id.eventRowCategory);
            textViewDateTime = itemView.findViewById(R.id.eventRowDateTime);
            textViewDescription = itemView.findViewById(R.id.eventRowDescription);

        }
    }
}
