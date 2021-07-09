package com.example.myclassroom.adapter;

import android.content.Context;
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
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context mContext;
    List<StudentsData.StudentsDummy> studentsDummyList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public StudentAdapter(Context mContext, List<StudentsData.StudentsDummy> studentsDummyList) {
        this.mContext = mContext;
        this.studentsDummyList = studentsDummyList;
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
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
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
//        holder.StudentName.setText(studentsDummyList.get(position).getStudentName());
//        holder.StudentNRP.setText(studentsDummyList.get(position).getStudentNRP());
    }

    @Override
    public int getItemCount() {
        return studentsDummyList.size();
    }

}
