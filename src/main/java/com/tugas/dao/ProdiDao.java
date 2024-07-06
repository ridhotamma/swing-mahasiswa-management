package com.tugas.dao;

import com.tugas.db.DatabaseConnection;
import com.tugas.model.Prodi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdiDao {
    public List<Prodi> getAllProdi() {
        List<Prodi> prodiList = new ArrayList<>();
        String query = "SELECT * FROM Prodi";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Prodi prodi = new Prodi(rs.getString("Prodi"), rs.getString("Nama_Prodi"));
                prodiList.add(prodi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prodiList;
    }

    public void addProdi(Prodi prodi) {
        String query = "INSERT INTO Prodi (Prodi, Nama_Prodi) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, prodi.getProdi());
            pstmt.setString(2, prodi.getNamaProdi());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProdi(Prodi prodi) {
        String query = "UPDATE Prodi SET Nama_Prodi = ? WHERE Prodi = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, prodi.getNamaProdi());
            pstmt.setString(2, prodi.getProdi());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProdi(String prodi) {
        String query = "DELETE FROM Prodi WHERE Prodi = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, prodi);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
