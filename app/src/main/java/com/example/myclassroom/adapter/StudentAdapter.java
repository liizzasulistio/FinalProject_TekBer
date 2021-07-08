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
import com.example.myclassroom.data.DummyData;
import com.example.myclassroom.data.StudentsData;
import com.example.myclassroom.databinding.ActivityStudentListBinding;
import com.example.myclassroom.databinding.ItemStudentBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
//
//public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>
//{
//    private ArrayList<StudentsData> studentsData;
//
//    public StudentAdapter(ArrayList<StudentsData> studentsData)
//    {
//        this.studentsData = studentsData;
//    }
//
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
//       return new RecyclerView.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//}


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
            setupItemWiew();
        }

        private void setupItemWiew() {
            StudentName = itemView.findViewById(R.id.name_txt);
            StudentNRP = itemView.findViewById(R.id.nrp_txt);
//            NamaKelas = itemView.findViewById(R.id.tv_nama_kelas);
//            TokenKelas = itemView.findViewById(R.id.tv_token_kelas);
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

//        bind data
        holder.StudentName.setText(studentsDummyList.get(position).getStudentName());
        holder.StudentNRP.setText(studentsDummyList.get(position).getStudentNRP());
    }

    @Override
    public int getItemCount() {
        return studentsDummyList.size();
    }
}
