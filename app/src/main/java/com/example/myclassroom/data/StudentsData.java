package com.example.myclassroom.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Map;
//
//public class StudentsData implements Parcelable{
//    private int studentID;
//    private int studentAva;
//    private String studentName;
//    private String studentNRP;
//
//    public StudentsData(int studentID, int studentAva, String studentName, String studentNRP) {
//        this.studentID = studentID;
//        this.studentAva = studentAva;
//        this.studentName = studentName;
//        this.studentNRP = studentNRP;
//    }
//
//    public int getStudentID() {
//        return studentID;
//    }
//
//    public int getStudentAva() {
//        return studentAva;
//    }
//
//    public String getStudentName()
//    {
//        return studentName;
//    }
//
//    public String getStudentNRP()
//    {
//        return studentNRP;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//
//    }
//}


public class StudentsData {
    public static class StudentsDummy{

        String id;
        String classID;
//        int studentAva;
        String studentName;
        String studentNRP;

        public StudentsDummy(String id, String classID, String studentName, String studentNRP) {
            this.id = id;
            this.classID = classID;
//            this.studentAva = studentAva;
            this.studentName = studentName;
            this.studentNRP = studentNRP;
        }

        public String getClassID() { return  classID; }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setClassID(String classID) {
            this.classID = classID;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getStudentNRP() {
            return studentNRP;
        }

        public void setStudentNRP(String token_kelas) {
            this.studentNRP = studentNRP;
        }

//        public class FirestoreClass {
//            public String name;
//            public String token;
//            public ArrayList<Map<String, Object>> students;
//            public String owner_id;
//
//            public FirestoreClass() {}
//        }
    }
}
