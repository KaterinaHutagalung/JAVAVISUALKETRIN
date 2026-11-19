
package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class AppTheme {

    public static final Color BACKGROUND = new Color(20, 24, 35);
    public static final Color PANEL = new Color(31, 38, 55);
    public static final Color CARD = new Color(39, 48, 68);
    public static final Color TEXT = new Color(240, 244, 255);
    public static final Color GOLD = new Color(255, 193, 7);
    public static final Color MUTED = new Color(160, 170, 190);

    public static Font titleFont() {
        return new Font("SansSerif", Font.BOLD, 20);
    }

    public static Font normalFont() {
        return new Font("SansSerif", Font.PLAIN, 14);
    }

    public static Font smallFont() {
        return new Font("SansSerif", Font.PLAIN, 12);
    }

    public static Font boldFont() {
        return new Font("SansSerif", Font.BOLD, 14);
    }

    // Warna ditentukan berdasarkan nama tipe karakter
    public static Color colorFor(Object type) {

        if (type == null) {
            return MUTED;
        }

        switch (type.toString().toUpperCase()) {

            case "WARRIOR":
                return new Color(230, 75, 75);

            case "MAGE":
                return new Color(145, 100, 230);

            case "ARCHER":
                return new Color(50, 190, 120);

            case "HEALER":
                return new Color(50, 170, 210);

            default:
                return MUTED;
        }
    }

    public static Border borderFor(Object type) {
        return BorderFactory.createLineBorder(
                colorFor(type), 2, true
        );
    }
}
