package com.example.pasture.View;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pasture.Model.Event;
import com.example.pasture.R;

import java.util.List;

public class OverViewAdapter extends RecyclerView.Adapter<OverViewAdapter.ViewHolder> {

    private List<Event> events;

    public OverViewAdapter(List<Event> events){
        this.events = events;
    }

    @NonNull
    @Override
    public OverViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.overview_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OverViewAdapter.ViewHolder holder, int position) {
        holder.time.setText(events.get(position).getDescription());
        holder.title.setText(events.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            title = itemView.findViewById(R.id.title);
        }
    }
}
