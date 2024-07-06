package com.tugas.controller;

import com.tugas.dao.ProdiDao;
import com.tugas.model.Prodi;
import com.tugas.view.ProdiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdiController {
    private ProdiView view;
    private ProdiDao dao;

    public ProdiController(ProdiView view, ProdiDao dao) {
        this.view = view;
        this.dao = dao;

        this.view.addSaveButtonListener(new SaveButtonListener());
        this.view.addUpdateButtonListener(new UpdateButtonListener());
        this.view.addDeleteButtonListener(new DeleteButtonListener());
        updateProdiTable();
    }

    private void updateProdiTable() {
        view.setProdiList(dao.getAllProdi());
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.validateForm()) {
                Prodi prodi = new Prodi(view.getProdi(), view.getNamaProdi());
                dao.addProdi(prodi);
                view.clearForm();
                updateProdiTable();
            }
        }
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.validateForm()) {
                Prodi prodi = new Prodi(view.getProdi(), view.getNamaProdi());
                dao.updateProdi(prodi);
                view.clearForm();
                updateProdiTable();
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dao.deleteProdi(view.getProdi());
            view.clearForm();
            updateProdiTable();
        }
    }
}
