package com.example.myclassroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myclassroom.R;
import com.example.myclassroom.data.DummyData;

import java.util.List;

public class AdapterKelasSendiri extends RecyclerView.Adapter<AdapterKelasSendiri.KelasSendiriViewHolder> {

    Context mContext;
    List<DummyData.DataKelas> mData;

    public AdapterKelasSendiri(Context mContext, List<DummyData.DataKelas> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public class KelasSendiriViewHolder extends RecyclerView.ViewHolder {

        TextView NamaKelas,TokenKelas;

        public KelasSendiriViewHolder(@NonNull View itemView) {
            super(itemView);
            setupItemWiew();
        }

        private void setupItemWiew() {
            NamaKelas = itemView.findViewById(R.id.tv_nama_kelas);
            TokenKelas = itemView.findViewById(R.id.tv_token_kelas);
        }
    }

    @NonNull
    @Override
    public AdapterKelasSendiri.KelasSendiriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_kelas,parent,false);

        return new AdapterKelasSendiri.KelasSendiriViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKelasSendiri.KelasSendiriViewHolder holder, int position) {

//        bind data
        holder.NamaKelas.setText(mData.get(position).getNama_kelas());
        holder.TokenKelas.setText(mData.get(position).getToken_kelas());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
