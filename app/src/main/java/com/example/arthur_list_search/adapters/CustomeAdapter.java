package com.example.arthur_list_search.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arthur_list_search.R;
import com.example.arthur_list_search.models.Data;

import java.util.ArrayList;
import java.util.List;

public class CustomeAdapter
        extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder>
        implements Filterable {

    private final List<Data> fullList;
    private final List<Data> filteredList;

    public CustomeAdapter(List<Data> list) {
        this.fullList     = new ArrayList<>(list);
        this.filteredList = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data item = filteredList.get(position);
        holder.username.setText(item.getName());
        holder.nameVersion.setText(item.getVersion());
        holder.imageViewItem.setImageResource(item.getImage());

        holder.itemView.setOnClickListener(v ->
                Toast.makeText(v.getContext(),
                                "You clicked: " + item.getName(),
                                Toast.LENGTH_SHORT)
                        .show()
        );
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected Filter.FilterResults performFiltering(CharSequence cs) {
                String query = cs.toString().trim().toLowerCase();
                List<Data> tempList = new ArrayList<>();
                if (query.isEmpty()) {
                    tempList.addAll(fullList);
                } else {
                    for (Data d : fullList) {
                        if (d.getName().toLowerCase().contains(query)) {
                            tempList.add(d);
                        }
                    }
                }
                Filter.FilterResults results = new Filter.FilterResults();
                results.values = tempList;
                return results;
            }

            @Override
            @SuppressWarnings("unchecked")
            protected void publishResults(CharSequence cs, Filter.FilterResults fr) {
                filteredList.clear();
                filteredList.addAll((List<Data>) fr.values);
                notifyDataSetChanged();
            }
        };
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, nameVersion;
        ImageView imageViewItem;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username      = itemView.findViewById(R.id.textName);
            nameVersion   = itemView.findViewById(R.id.textVer);
            imageViewItem = itemView.findViewById(R.id.imageView);
        }
    }
}
