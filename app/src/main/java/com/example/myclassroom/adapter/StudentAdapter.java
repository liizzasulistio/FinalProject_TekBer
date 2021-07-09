package com.example.myclassroom.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myclassroom.R;
import com.example.myclassroom.data.StudentsData;
import com.example.myclassroom.screens.StudentDetail;
import com.example.myclassroom.screens.StudentListActivity;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context mContext;
    List<StudentsData.StudentsDummy> mData;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public StudentAdapter(Context mContext, List<StudentsData.StudentsDummy> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        ImageView StudentAva;
        TextView StudentName,StudentNRP;

        public StudentViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            StudentName = itemView.findViewById(R.id.name_txt);
            StudentNRP = itemView.findViewById(R.id.nrp_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Log.e("nama kelas",mData.get(position).getId());
                    Intent intent = new Intent(mContext, StudentDetail.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("studentID", mData.get(position).getId());
                    intent.putExtra("student", mData.get(position).getStudentName());
                    intent.putExtra("student_nrp", mData.get(position).getStudentNRP());
                    mContext.startActivity(intent);
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position);
//                        }
//                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_student,parent,false);

        return new StudentViewHolder(layout, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.StudentName.setText(mData.get(position).getStudentName());
        holder.StudentNRP.setText(mData.get(position).getStudentNRP());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
