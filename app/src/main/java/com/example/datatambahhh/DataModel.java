package com.example.datatambahhh; // Pastikan ini sesuai dengan nama paket Anda

public class DataModel {
    private int id;
    private String nama;
    private String telepon;

    public DataModel(int id, String nama, String telepon) {
        this.id = id;
        this.nama = nama;
        this.telepon = telepon;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getTelepon() {
        return telepon;
    }
}
