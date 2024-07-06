package com.tugas.model;

public class Prodi {
    private String prodi;
    private String namaProdi;

    public Prodi(String prodi, String namaProdi) {
        this.prodi = prodi;
        this.namaProdi = namaProdi;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getNamaProdi() {
        return namaProdi;
    }

    public void setNamaProdi(String namaProdi) {
        this.namaProdi = namaProdi;
    }
}
