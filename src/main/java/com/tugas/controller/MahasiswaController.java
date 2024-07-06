package com.tugas.controller;

import com.tugas.dao.MahasiswaDao;
import com.tugas.dao.ProdiDao;
import com.tugas.model.Mahasiswa;
import com.tugas.model.Prodi;
import com.tugas.view.MahasiswaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MahasiswaController {
    private MahasiswaView view;
    private MahasiswaDao dao;
    private ProdiDao prodiDao;

    public MahasiswaController(MahasiswaView view, MahasiswaDao dao, ProdiDao prodiDao) {
        this.view = view;
        this.dao = dao;
        this.prodiDao = prodiDao;

        this.view.addSaveButtonListener(new SaveButtonListener());
        this.view.addUpdateButtonListener(new UpdateButtonListener());
        this.view.addDeleteButtonListener(new DeleteButtonListener());
        this.view.addRefreshButtonListener(new RefreshButtonListener());
        this.view.setNimChangeListener(new NimChangeListener());

        updateProdiList();
        updateMahasiswaTable();
    }

    private void updateProdiList() {
        List<Prodi> prodiList = prodiDao.getAllProdi();
        view.setProdiList(prodiList);
    }

    private void updateMahasiswaTable() {
        view.setMahasiswaList(dao.getAllMahasiswa());
    }

    private void fillMahasiswaForm(Mahasiswa mhs) {
        view.setNama(mhs.getNama());
        view.setAlamat(mhs.getAlamat());
        view.setTelepon(mhs.getTelepon());
        view.setProdi(mhs.getProdi());
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.validateForm()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNim(view.getNim());
                mhs.setNama(view.getNama());
                mhs.setAlamat(view.getAlamat());
                mhs.setTelepon(view.getTelepon());
                mhs.setProdi(view.getProdi());
                dao.addMahasiswa(mhs);
                view.clearForm();
                updateMahasiswaTable();
            }
        }
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.validateForm()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNim(view.getNim());
                mhs.setNama(view.getNama());
                mhs.setAlamat(view.getAlamat());
                mhs.setTelepon(view.getTelepon());
                mhs.setProdi(view.getProdi());
                dao.updateMahasiswa(mhs);
                view.clearForm();
                updateMahasiswaTable();
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dao.deleteMahasiswa(view.getNim());
            view.clearForm();
            updateMahasiswaTable();
        }
    }

    private class RefreshButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateProdiList();
            updateMahasiswaTable();
            view.clearForm();
        }
    }

    private class NimChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nim = view.getNim();
            Mahasiswa mhs = dao.getMahasiswaByNim(nim);
            if (mhs != null) {
                fillMahasiswaForm(mhs);
            }
        }
    }
}
