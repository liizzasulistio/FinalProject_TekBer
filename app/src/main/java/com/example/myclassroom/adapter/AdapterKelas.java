package com.example.myclassroom.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myclassroom.R;
import com.example.myclassroom.data.DummyData;
import com.example.myclassroom.data.StudentsData;
import com.example.myclassroom.screens.ListKelasActivity;
import com.example.myclassroom.screens.StudentListActivity;

import java.util.List;

public class AdapterKelas extends RecyclerView.Adapter<AdapterKelas.KelasViewHolder> {

    Context mContext;
    List<DummyData.DataKelas> mData;
    private AdapterKelas.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdapterKelas.OnItemClickListener listener) {
        mListener = listener;
    }


    public AdapterKelas(Context mContext, List<DummyData.DataKelas> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    public class KelasViewHolder extends RecyclerView.ViewHolder {

        TextView NamaKelas,TokenKelas;
        RelativeLayout RlItemKelas;

//        public KelasViewHolder(@NonNull View itemView) {
//            super(itemView);
////            setupItemWiew();
//        }

        public KelasViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            NamaKelas = itemView.findViewById(R.id.tv_nama_kelas);
            TokenKelas = itemView.findViewById(R.id.tv_token_kelas);

            RlItemKelas = itemView.findViewById(R.id.rl_item_kelas);

            RlItemKelas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                  int position = getAdapterPosition();
                    Log.e("nama kelas",mData.get(position).getId());
                    Intent intent = new Intent(mContext, StudentListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("EXTRA_ID_KELAS", mData.get(position).getId());
                    mContext.startActivity(intent);

//                    Toast.makeText(mContext, String.valueOf(position) , Toast.LENGTH_SHORT).show();
//                    if (position != RecyclerView.NO_POSITION) {
//                        mListener.onItemClick(position);
//                    }
//                    if (mListener != null) {
//                        Toast.makeText(mContext, "test2", Toast.LENGTH_SHORT).show();
////                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position);
//                        }
//                    }
                }
            });
        }

//        private void setupItemWiew() {
//            NamaKelas = itemView.findViewById(R.id.tv_nama_kelas);
//            TokenKelas = itemView.findViewById(R.id.tv_token_kelas);
//        }
    }

    @NonNull
    @Override
    public KelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_kelas,parent,false);

        return new KelasViewHolder(layout,mListener);
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
