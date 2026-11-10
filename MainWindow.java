package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private CharacterManager manager;
    private JLabel countLabel;
    private JTextArea characterArea;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel(
            "CHARACTER MANAGER",
            SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        characterArea = new JTextArea();
        characterArea.setEditable(false);
        characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(characterArea);
        add(scrollPane, BorderLayout.CENTER);

        countLabel = new JLabel("", SwingConstants.CENTER);
        add(countLabel, BorderLayout.SOUTH);

        updateCharacterList();
    }

    private void updateCharacterList() {

        characterArea.setText("");

        for (Character character : manager.getCharacterList()) {

            characterArea.append(
                "Nama  : " + character.getName() + "\n" +
                "Tipe  : " + character.getType() + "\n" +
                "Level : " + character.getLevel() + "\n" +
                "--------------------------\n"
            );
        }

        countLabel.setText(
            "Jumlah Karakter: " + manager.getCharacterCount()
        );
    }
}