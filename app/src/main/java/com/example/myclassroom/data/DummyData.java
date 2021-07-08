package com.example.myclassroom.data;

import java.util.ArrayList;
import java.util.Map;

public class DummyData {
    public static class DataKelas{

        String id;
        String nama_kelas;
        String token_kelas;
        String id_created;

        public DataKelas(String id, String nama_kelas, String token_kelas, String id_created) {
            this.id = id;
            this.nama_kelas = nama_kelas;
            this.token_kelas = token_kelas;
            this.id_created = id_created;
        }

        public String getId_created() {
            return id_created;
        }

        public void setId_created(String id_created) {
            this.id_created = id_created;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNama_kelas() {
            return nama_kelas;
        }

        public void setNama_kelas(String nama_kelas) {
            this.nama_kelas = nama_kelas;
        }

        public String getToken_kelas() {
            return token_kelas;
        }

        public void setToken_kelas(String token_kelas) {
            this.token_kelas = token_kelas;
        }
    }

    public class FirestoreClass {
        public String name;
        public String token;
        public ArrayList<Map<String, Object>> students;
        public String owner_id;

        public FirestoreClass() {}
    }
}