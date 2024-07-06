package com.tugas.view;

import com.tugas.model.Mahasiswa;
import com.tugas.model.Prodi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MahasiswaView extends JPanel {
    private JTextField nimField, namaField, alamatField, teleponField;
    private JComboBox<String> prodiComboBox;
    private JButton saveButton, updateButton, deleteButton, refreshButton;
    private JTable mahasiswaTable;
    private JLabel nimErrorLabel, namaErrorLabel, alamatErrorLabel, teleponErrorLabel, prodiErrorLabel;

    private ActionListener nimChangeListener;

    public MahasiswaView() {
        setLayout(new BorderLayout());

        EmptyBorder padding = new EmptyBorder(10, 10, 10, 10);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nimLabel = new JLabel("NIM:");
        nimField = new JTextField(20);
        nimField.setBorder(BorderFactory.createCompoundBorder(nimField.getBorder(), padding));
        nimErrorLabel = new JLabel();
        nimErrorLabel.setForeground(Color.RED);

        JLabel namaLabel = new JLabel("Nama:");
        namaField = new JTextField(20);
        namaField.setBorder(BorderFactory.createCompoundBorder(namaField.getBorder(), padding));
        namaErrorLabel = new JLabel();
        namaErrorLabel.setForeground(Color.RED);

        JLabel alamatLabel = new JLabel("Alamat:");
        alamatField = new JTextField(20);
        alamatField.setBorder(BorderFactory.createCompoundBorder(alamatField.getBorder(), padding));
        alamatErrorLabel = new JLabel();
        alamatErrorLabel.setForeground(Color.RED);

        JLabel teleponLabel = new JLabel("Telepon:");
        teleponField = new JTextField(20);
        teleponField.setBorder(BorderFactory.createCompoundBorder(teleponField.getBorder(), padding));
        teleponErrorLabel = new JLabel();
        teleponErrorLabel.setForeground(Color.RED);

        JLabel prodiLabel = new JLabel("Prodi:");
        prodiComboBox = new JComboBox<>();
        prodiErrorLabel = new JLabel();
        prodiErrorLabel.setForeground(Color.RED);

        saveButton = new JButton("Simpan");
        updateButton = new JButton("Ubah");
        deleteButton = new JButton("Hapus");
        refreshButton = new JButton("Refresh");

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nimLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nimField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(nimErrorLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(namaLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(namaField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(namaErrorLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(alamatLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(alamatField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(alamatErrorLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        formPanel.add(teleponLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        formPanel.add(teleponField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 11;
        formPanel.add(teleponErrorLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        formPanel.add(prodiLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 13;
        formPanel.add(prodiComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 14;
        formPanel.add(prodiErrorLabel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        gbc.gridx = 0;
        gbc.gridy = 15;
        formPanel.add(buttonPanel, gbc);

        mahasiswaTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(mahasiswaTable);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tableScrollPane);
        splitPane.setDividerLocation(1.0 / 3.0);

        add(splitPane, BorderLayout.CENTER);

        // Add DocumentListener to nimField to detect changes
        nimField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleNimChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleNimChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleNimChange();
            }

            private void handleNimChange() {
                String nim = nimField.getText();
                if (nim.length() > 1) {
                    if (nimChangeListener != null) {
                        nimChangeListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                    }
                }
            }
        });
    }

    // Set NIM change listener
    public void setNimChangeListener(ActionListener listener) {
        this.nimChangeListener = listener;
    }

    public String getNim() {
        return nimField.getText();
    }

    public String getNama() {
        return namaField.getText();
    }

    public void setNama(String nama) {
        namaField.setText(nama);
    }

    public String getAlamat() {
        return alamatField.getText();
    }

    public void setAlamat(String alamat) {
        alamatField.setText(alamat);
    }

    public String getTelepon() {
        return teleponField.getText();
    }

    public void setTelepon(String telepon) {
        teleponField.setText(telepon);
    }

    public String getProdi() {
        return (String) prodiComboBox.getSelectedItem();
    }

    public void setProdi(String prodi) {
        prodiComboBox.setSelectedItem(prodi);
    }

    public void setMahasiswaList(List<Mahasiswa> mahasiswaList) {
        String[] columnNames = {"NIM", "Nama", "Alamat", "Telepon", "Prodi"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Mahasiswa mhs : mahasiswaList) {
            Object[] row = {
                    mhs.getNim(),
                    mhs.getNama(),
                    mhs.getAlamat(),
                    mhs.getTelepon(),
                    mhs.getProdi()
            };
            model.addRow(row);
        }

        mahasiswaTable.setModel(model);
    }

    public void setProdiList(List<Prodi> prodiList) {
        prodiComboBox.removeAllItems();
        for (Prodi prodi : prodiList) {
            prodiComboBox.addItem(prodi.getProdi());
        }
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

    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public void clearForm() {
        nimField.setText("");
        namaField.setText("");
        alamatField.setText("");
        teleponField.setText("");
        prodiComboBox.setSelectedIndex(-1);

        nimErrorLabel.setText("");
        namaErrorLabel.setText("");
        alamatErrorLabel.setText("");
        teleponErrorLabel.setText("");
        prodiErrorLabel.setText("");
    }

    public boolean validateForm() {
        final int NIM_MAX_LENGTH = 10;
        final int NAMA_MAX_LENGTH = 30;
        final int ALAMAT_MAX_LENGTH = 200;
        final int TELEPON_MAX_LENGTH = 15;

        boolean isValid = true;

        if (getNim().isEmpty()) {
            nimErrorLabel.setText("NIM tidak boleh kosong");
            isValid = false;
        } else if (!getNim().matches("\\d+")) {
            nimErrorLabel.setText("NIM harus berupa angka");
            isValid = false;
        } else if (getNim().length() > NIM_MAX_LENGTH) {
            nimErrorLabel.setText("NIM tidak boleh lebih dari " + NIM_MAX_LENGTH + " karakter");
            isValid = false;
        } else {
            nimErrorLabel.setText("");
        }

        if (getNama().isEmpty()) {
            namaErrorLabel.setText("Nama tidak boleh kosong");
            isValid = false;
        } else if (!getNama().matches("[a-zA-Z\\s]+")) {
            namaErrorLabel.setText("Nama harus berupa huruf");
            isValid = false;
        } else if (getNama().length() > NAMA_MAX_LENGTH) {
            namaErrorLabel.setText("Nama tidak boleh lebih dari " + NAMA_MAX_LENGTH + " karakter");
            isValid = false;
        } else {
            namaErrorLabel.setText("");
        }

        if (getAlamat().isEmpty()) {
            alamatErrorLabel.setText("Alamat tidak boleh kosong");
            isValid = false;
        } else if (getAlamat().length() > ALAMAT_MAX_LENGTH) {
            alamatErrorLabel.setText("Alamat tidak boleh lebih dari " + ALAMAT_MAX_LENGTH + " karakter");
            isValid = false;
        } else {
            alamatErrorLabel.setText("");
        }

        if (getTelepon().isEmpty()) {
            teleponErrorLabel.setText("Telepon tidak boleh kosong");
            isValid = false;
        } else if (!getTelepon().matches("\\d+")) {
            teleponErrorLabel.setText("Telepon harus berupa angka");
            isValid = false;
        } else if (getTelepon().length() > TELEPON_MAX_LENGTH) {
            teleponErrorLabel.setText("Telepon tidak boleh lebih dari " + TELEPON_MAX_LENGTH + " karakter");
            isValid = false;
        } else {
            teleponErrorLabel.setText("");
        }

        if (prodiComboBox.getSelectedItem() == null) {
            prodiErrorLabel.setText("Prodi tidak boleh kosong");
            isValid = false;
        } else {
            prodiErrorLabel.setText("");
        }

        return isValid;
    }
}
