package com.example.myclassroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myclassroom.R;
import com.example.myclassroom.data.DummyData;

import java.util.List;

public class AdapterKelas extends RecyclerView.Adapter<AdapterKelas.KelasViewHolder> {

    Context mContext;
    List<DummyData.DataKelas> mData;

    public AdapterKelas(Context mContext, List<DummyData.DataKelas> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public class KelasViewHolder extends RecyclerView.ViewHolder {

        TextView NamaKelas,TokenKelas;

        public KelasViewHolder(@NonNull View itemView) {
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
    public KelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_kelas,parent,false);

        return new KelasViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull KelasViewHolder holder, int position) {

//        bind data
        holder.NamaKelas.setText(mData.get(position).getNama_kelas());
        holder.TokenKelas.setText(mData.get(position).getToken_kelas());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
