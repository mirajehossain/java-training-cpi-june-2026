package com.training.advancedcalculator;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdvancedCalculatorApp().setVisible(true));
    }
}
