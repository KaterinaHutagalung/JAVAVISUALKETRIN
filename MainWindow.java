
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    private JPanel characterContainer;
    private JLabel statusLabel;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - TP10");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(AppTheme.BACKGROUND);

        createHeader();
        createCharacterList();
        createLegend();
        createStatusBar();

        refreshCharacters();

        pack();
        setLocationRelativeTo(null);
    }

    // NORTH - HEADER
    private void createHeader() {

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(AppTheme.PANEL);
        header.setBorder(
            BorderFactory.createEmptyBorder(18, 20, 18, 20)
        );

        JLabel title = new JLabel("CHARACTER MANAGER");
        title.setForeground(AppTheme.GOLD);
        title.setFont(AppTheme.titleFont());

        JLabel subtitle = new JLabel("Manage Your Characters");
        subtitle.setForeground(AppTheme.MUTED);
        subtitle.setFont(AppTheme.smallFont());

        JPanel text = new JPanel();
        text.setLayout(
            new BoxLayout(text, BoxLayout.Y_AXIS)
        );
        text.setBackground(AppTheme.PANEL);

        text.add(title);
        text.add(Box.createVerticalStrut(5));
        text.add(subtitle);

        header.add(text, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);
    }

    // CENTER - CHARACTER LIST
    private void createCharacterList() {

        characterContainer = new JPanel();

        characterContainer.setLayout(
            new BoxLayout(
                characterContainer,
                BoxLayout.Y_AXIS
            )
        );

        characterContainer.setBackground(AppTheme.BACKGROUND);

        characterContainer.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JScrollPane scroll = new JScrollPane(characterContainer);

        scroll.setPreferredSize(new Dimension(550, 400));

        scroll.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scroll.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scroll.getViewport().setBackground(AppTheme.BACKGROUND);

        add(scroll, BorderLayout.CENTER);
    }

    // EAST - LEGEND
    private void createLegend() {

        JPanel legend = new JPanel();

        legend.setLayout(
            new BoxLayout(legend, BoxLayout.Y_AXIS)
        );

        legend.setBackground(AppTheme.PANEL);

        legend.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel title = new JLabel("CHARACTER LEGEND");
        title.setForeground(AppTheme.GOLD);
        title.setFont(AppTheme.boldFont());

        legend.add(title);
        legend.add(Box.createVerticalStrut(15));

        for (CharacterType type : CharacterType.values()) {

            JPanel item = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 8, 5)
            );

            item.setBackground(AppTheme.PANEL);

            JLabel color = new JLabel("     ");
            color.setOpaque(true);
            color.setBackground(AppTheme.colorFor(type));

            JLabel name = new JLabel(type.toString());
            name.setForeground(AppTheme.TEXT);
            name.setFont(AppTheme.normalFont());

            item.add(color);
            item.add(name);

            legend.add(item);
            legend.add(Box.createVerticalStrut(8));
        }

        legend.add(Box.createVerticalGlue());

        add(legend, BorderLayout.EAST);
    }

    // SOUTH - STATUS BAR
    private void createStatusBar() {

        JPanel status = new JPanel(new BorderLayout());

        status.setBackground(AppTheme.PANEL);

        status.setBorder(
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        );

        statusLabel = new JLabel("Total Characters: 0");
        statusLabel.setForeground(AppTheme.TEXT);
        statusLabel.setFont(AppTheme.smallFont());

        JLabel footer = new JLabel("Character Manager | TP10");
        footer.setForeground(AppTheme.MUTED);
        footer.setFont(AppTheme.smallFont());

        status.add(statusLabel, BorderLayout.WEST);
        status.add(footer, BorderLayout.EAST);

        add(status, BorderLayout.SOUTH);
    }

    // GETTER HELPER
    private Object getValue(Object object, String... names) {

        for (String name : names) {

            try {
                Method method = object.getClass().getMethod(name);
                return method.invoke(object);

            } catch (Exception ignored) {
                // Mencoba nama getter berikutnya
            }
        }

        return null;
    }

    // MENGAMBIL DAFTAR KARAKTER
    private List<Object> getCharacters() {

        String[] possibleMethods = {
            "getAllCharacters",
            "getCharacters",
            "getCharacterList"
        };

        for (String name : possibleMethods) {

            try {
                Method method = manager.getClass().getMethod(name);
                Object result = method.invoke(manager);

                if (result instanceof Collection<?>) {
                    return new ArrayList<>((Collection<?>) result);
                }

            } catch (Exception ignored) {
                // Mencoba method berikutnya
            }
        }

        // Mencari method tanpa parameter yang mengembalikan Collection
        for (Method method : manager.getClass().getMethods()) {

            try {
                if (method.getParameterCount() == 0
                    && Collection.class.isAssignableFrom(
                        method.getReturnType()
                    )) {

                    Object result = method.invoke(manager);

                    if (result instanceof Collection<?>) {
                        return new ArrayList<>(
                            (Collection<?>) result
                        );
                    }
                }

            } catch (Exception ignored) {
                // Melanjutkan pencarian
            }
        }

        return new ArrayList<>();
    }

    // CHARACTER CARD
    private JPanel createCharacterCard(Object character) {

        Object nameValue = getValue(character, "getName");
        Object typeValue = getValue(character, "getType");
        Object levelValue = getValue(character, "getLevel");

        Object hpValue = getValue(
            character, "getHitPoints", "getHp"
        );

        Object manaValue = getValue(
            character, "getMana"
        );

        JPanel card = new JPanel(new BorderLayout(10, 5));

        card.setBackground(AppTheme.CARD);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                AppTheme.borderFor(typeValue),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
            )
        );

        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel name = new JLabel(
            String.valueOf(nameValue)
        );

        name.setForeground(AppTheme.TEXT);
        name.setFont(AppTheme.boldFont());

        JLabel type = new JLabel(
            "Class: " + typeValue
        );

        type.setForeground(AppTheme.colorFor(typeValue));
        type.setFont(AppTheme.normalFont());

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(AppTheme.CARD);

        left.add(name);
        left.add(Box.createVerticalStrut(7));
        left.add(type);

        JLabel level = new JLabel("LEVEL: " + levelValue);
        level.setForeground(AppTheme.GOLD);
        level.setFont(AppTheme.boldFont());

        JLabel hp = new JLabel("HP: " + hpValue);
        hp.setForeground(AppTheme.TEXT);

        JLabel mana = new JLabel("MANA: " + manaValue);
        mana.setForeground(AppTheme.MUTED);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBackground(AppTheme.CARD);

        right.add(level);
        right.add(Box.createVerticalStrut(5));
        right.add(hp);
        right.add(mana);

        card.add(left, BorderLayout.CENTER);
        card.add(right, BorderLayout.EAST);

        return card;
    }

    // REFRESH
    public void refreshCharacters() {

        characterContainer.removeAll();

        List<Object> characters = getCharacters();

        for (Object character : characters) {

            characterContainer.add(
                createCharacterCard(character)
            );

            characterContainer.add(
                Box.createVerticalStrut(12)
            );
        }

        characterContainer.revalidate();
        characterContainer.repaint();

        statusLabel.setText(
            "Total Characters: " + characters.size()
        );
    }
}
