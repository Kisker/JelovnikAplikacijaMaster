package com.ftninformatika.zadatak.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ftninformatika.zadatak.Providers.JeloProvider;
import com.ftninformatika.zadatak.R;

import java.util.List;

public class KategorijeRecyclerAdapter extends RecyclerView.Adapter<KategorijeRecyclerAdapter.MyViewHolder> {

    private List<String> data;
    private OnElementClickListener listener;

    public KategorijeRecyclerAdapter(OnElementClickListener listener) {
        this.listener = listener;
        data = JeloProvider.getKategorije();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(listener, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvText;
        private View wholeView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
            wholeView = itemView;
        }

        public void bind(final OnElementClickListener listener, final String item) {
            tvText.setText(item);
            wholeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onElementClicked(item);
                }
            });
        }
    }

    public interface OnElementClickListener {
        void onElementClicked(String kategorija);
    }
}
