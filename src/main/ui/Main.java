package ui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ElectionAppFrameGUI().setVisible(true);
        });
    }

    private static void createAndShowGUI() {
        ElectionAppFrameGUI frame = new ElectionAppFrameGUI();

        frame.setVisible(true);
    }
}