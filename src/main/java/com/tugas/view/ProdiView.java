package com.tugas.view;

import com.tugas.model.Prodi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ProdiView extends JPanel {
    private JTextField prodiField, namaProdiField;
    private JButton saveButton, updateButton, deleteButton;
    private JTable prodiTable;
    private JLabel prodiErrorLabel, namaProdiErrorLabel;

    public ProdiView() {
        setLayout(new BorderLayout());

        EmptyBorder padding = new EmptyBorder(10, 10, 10, 10);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel prodiLabel = new JLabel("Prodi:");
        prodiField = new JTextField(20);
        prodiField.setBorder(BorderFactory.createCompoundBorder(prodiField.getBorder(), padding));
        prodiErrorLabel = new JLabel();
        prodiErrorLabel.setForeground(Color.RED);

        JLabel namaProdiLabel = new JLabel("Nama Prodi:");
        namaProdiField = new JTextField(20);
        namaProdiField.setBorder(BorderFactory.createCompoundBorder(namaProdiField.getBorder(), padding));
        namaProdiErrorLabel = new JLabel();
        namaProdiErrorLabel.setForeground(Color.RED);

        saveButton = new JButton("Simpan");
        updateButton = new JButton("Ubah");
        deleteButton = new JButton("Hapus");

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(prodiLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(prodiField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(prodiErrorLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(namaProdiLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(namaProdiField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(namaProdiErrorLabel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(buttonPanel, gbc);

        prodiTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(prodiTable);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tableScrollPane);
        splitPane.setDividerLocation(1.0 / 3.0);

        add(splitPane, BorderLayout.CENTER);
    }

    public String getProdi() {
        return prodiField.getText();
    }

    public String getNamaProdi() {
        return namaProdiField.getText();
    }

    public void setProdiList(List<Prodi> prodiList) {
        String[] columnNames = {"Prodi", "Nama Prodi"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Prodi prodi : prodiList) {
            Object[] row = {prodi.getProdi(), prodi.getNamaProdi()};
            model.addRow(row);
        }

        prodiTable.setModel(model);
    }

    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void addUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void clearForm() {
        prodiField.setText("");
        namaProdiField.setText("");
        prodiErrorLabel.setText("");
        namaProdiErrorLabel.setText("");
    }

    public boolean validateForm() {
        final int PRODI_MAX_LENGTH = 2;
        final int NAMA_PRODI_MAX_LENGTH = 100;

        boolean isValid = true;

        if (getProdi().isEmpty()) {
            prodiErrorLabel.setText("Prodi tidak boleh kosong");
            isValid = false;
        } else if (getProdi().length() > PRODI_MAX_LENGTH) {
            prodiErrorLabel.setText("Prodi tidak boleh lebih dari " + PRODI_MAX_LENGTH + " karakter");
            isValid = false;
        } else {
            prodiErrorLabel.setText("");
        }

        if (getNamaProdi().isEmpty()) {
            namaProdiErrorLabel.setText("Nama Prodi tidak boleh kosong");
            isValid = false;
        } else if (getNamaProdi().length() > NAMA_PRODI_MAX_LENGTH) {
            namaProdiErrorLabel.setText("Nama Prodi tidak boleh lebih dari " + NAMA_PRODI_MAX_LENGTH + " karakter");
            isValid = false;
        } else {
            namaProdiErrorLabel.setText("");
        }

        return isValid;
    }
}
