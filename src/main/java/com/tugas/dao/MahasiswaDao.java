package com.tugas.dao;

import com.tugas.db.DatabaseConnection;
import com.tugas.model.Mahasiswa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDao {
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        String query = "SELECT * FROM Mahasiswa";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNim(rs.getString("Nim"));
                mhs.setNama(rs.getString("Nama"));
                mhs.setAlamat(rs.getString("Alamat"));
                mhs.setTelepon(rs.getString("Telepon"));
                mhs.setProdi(rs.getString("Prodi"));
                mahasiswaList.add(mhs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mahasiswaList;
    }

    public void addMahasiswa(Mahasiswa mhs) {
        String query = "INSERT INTO Mahasiswa (Nim, Nama, Alamat, Telepon, Prodi) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, mhs.getNim());
            pstmt.setString(2, mhs.getNama());
            pstmt.setString(3, mhs.getAlamat());
            pstmt.setString(4, mhs.getTelepon());
            pstmt.setString(5, mhs.getProdi());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMahasiswa(Mahasiswa mhs) {
        String query = "UPDATE Mahasiswa SET Nama = ?, Alamat = ?, Telepon = ?, Prodi = ? WHERE Nim = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, mhs.getNama());
            pstmt.setString(2, mhs.getAlamat());
            pstmt.setString(3, mhs.getTelepon());
            pstmt.setString(4, mhs.getProdi());
            pstmt.setString(5, mhs.getNim());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMahasiswa(String nim) {
        String query = "DELETE FROM Mahasiswa WHERE Nim = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nim);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Mahasiswa getMahasiswaByNim(String nim) {
        Mahasiswa mhs = null;
        String query = "SELECT * FROM Mahasiswa WHERE Nim = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nim);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                mhs = new Mahasiswa();
                mhs.setNim(rs.getString("Nim"));
                mhs.setNama(rs.getString("Nama"));
                mhs.setAlamat(rs.getString("Alamat"));
                mhs.setTelepon(rs.getString("Telepon"));
                mhs.setProdi(rs.getString("Prodi"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mhs;
    }
}
