package com.tugas;

import com.tugas.view.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.setVisible(true);
        });
    }
}
