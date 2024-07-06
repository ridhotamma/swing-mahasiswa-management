package com.tugas.view;

import com.tugas.controller.MahasiswaController;
import com.tugas.controller.ProdiController;
import com.tugas.dao.MahasiswaDao;
import com.tugas.dao.ProdiDao;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainView() {
        setTitle("Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem mahasiswaMenuItem = new JMenuItem("Mahasiswa");
        JMenuItem prodiMenuItem = new JMenuItem("Prodi");

        mahasiswaMenuItem.addActionListener(e -> showCard("MahasiswaPanel"));
        prodiMenuItem.addActionListener(e -> showCard("ProdiPanel"));

        menu.add(mahasiswaMenuItem);
        menu.add(prodiMenuItem);
        menuBar.add(menu);

        setJMenuBar(menuBar);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        MahasiswaDao mahasiswaDao = new MahasiswaDao();
        ProdiDao prodiDao = new ProdiDao();

        MahasiswaView mahasiswaView = new MahasiswaView();
        new MahasiswaController(mahasiswaView, mahasiswaDao, prodiDao);

        ProdiView prodiView = new ProdiView();
        new ProdiController(prodiView, prodiDao);

        cardPanel.add(mahasiswaView, "MahasiswaPanel");
        cardPanel.add(prodiView, "ProdiPanel");

        add(cardPanel, BorderLayout.CENTER);

        showCard("MahasiswaPanel");
    }

    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }
}
