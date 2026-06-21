package com.training.eventhandling;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventHandlingDemo().setVisible(true));
    }
}
