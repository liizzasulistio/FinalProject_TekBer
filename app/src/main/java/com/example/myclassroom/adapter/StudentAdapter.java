package com.example.myclassroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public StudentAdapter(Context mContext, List<StudentsData.StudentsDummy> studentsDummyList) {
        this.mContext = mContext;
        this.studentsDummyList = studentsDummyList;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        ImageView StudentAva;
        TextView StudentName,StudentNRP;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            setupItemView();
        }

        private void setupItemView() {
            StudentName = itemView.findViewById(R.id.name_txt);
            StudentNRP = itemView.findViewById(R.id.nrp_txt);
        }
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_student,parent,false);

        return new StudentViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.StudentName.setText(studentsDummyList.get(position).getStudentName());
        holder.StudentNRP.setText(studentsDummyList.get(position).getStudentNRP());
    }

    @Override
    public int getItemCount() {
        return studentsDummyList.size();
    }

}
