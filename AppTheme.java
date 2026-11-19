
package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.CharacterType;

public final class AppTheme {

    // WARNA UTAMA
    public static final Color BACKGROUND =
            new Color(225, 248, 250);

    public static final Color PANEL =
            new Color(255, 255, 255);

    public static final Color TEXT =
            new Color(35, 75, 85);

    public static final Color GOLD =
            new Color(230, 155, 35);

    public static final Color MUTED =
            new Color(100, 145, 155);

    public static final Color CYAN =
            new Color(0, 175, 190);

    // FONT
    public static final Font TITLE_FONT =
            new Font("SansSerif", Font.BOLD, 26);

    public static final Font NORMAL_FONT =
            new Font("SansSerif", Font.PLAIN, 14);

    public static final Font BOLD_FONT =
            new Font("SansSerif", Font.BOLD, 14);

    // BORDER
    public static final Border CARD_BORDER =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            new Color(120, 215, 220), 2),
                    BorderFactory.createEmptyBorder(
                            15, 18, 15, 18)
            );

    public static final Border MAIN_BORDER =
            BorderFactory.createEmptyBorder(
                    20, 20, 20, 20);

    public static final Border ROLE_BORDER =
            BorderFactory.createLineBorder(CYAN, 2);

    private AppTheme() {
    }

    // WARNA BERDASARKAN ROLE
    public static Color colorFor(CharacterType type) {

        if (type == null) {
            return PANEL;
        }

        switch (type) {

            case WARRIOR:
                return new Color(190, 235, 240);

            case MAGE:
                return new Color(215, 225, 250);

            case ARCHER:
                return new Color(205, 240, 220);

            case ASSASSIN:
                return new Color(230, 215, 245);

            default:
                return PANEL;
        }
    }
}
