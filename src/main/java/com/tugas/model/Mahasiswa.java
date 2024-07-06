package com.tugas.model;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String alamat;
    private String telepon;
    private String prodi;

    public Mahasiswa() {

    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getProdi() {
        return prodi;
    }
}
