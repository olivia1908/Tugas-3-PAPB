package com.example.datatambahhh; // Pastikan ini sesuai

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<DataModel> dataList;
    private OnItemClickListener onItemClickListener;

    public DataAdapter(List<DataModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel data = dataList.get(position);
        holder.textViewNama.setText(data.getNama());
        holder.textViewTelepon.setText(data.getTelepon());

        // Set listener untuk item klik
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position, data.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, int id);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNama;
        TextView textViewTelepon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.textViewNama);
            textViewTelepon = itemView.findViewById(R.id.textViewTelepon);
        }
    }
}
