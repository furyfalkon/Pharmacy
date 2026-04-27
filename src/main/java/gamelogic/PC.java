
package gamelogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PC {

    static double geld = 1000.0;

    public static void main(String[] args) {
        startScreen();
    }


    // Startbildschirm mit Bild
    public static void startScreen() {
        JFrame frame = new JFrame("LaptopFront");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel background = new JLabel(new ImageIcon("LaptopFront.png"));
        background.setLayout(null);

        frame.setContentPane(background);

        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                menuScreen();
            }
        });

        frame.setVisible(true);
    }

    // Menü mit Bild + Buttons
    public static void menuScreen() {
        JFrame frame = new JFrame("DesktopComputer");
        frame.setSize(600, 400);

        JLabel background = new JLabel(new ImageIcon("DesktopComputer.png"));
        background.setLayout(null);
        frame.setContentPane(background);

        JButton importBtn = new JButton("Import");
        JButton exportBtn = new JButton("Export");

        importBtn.setBounds(200, 150, 200, 40);
        exportBtn.setBounds(200, 220, 200, 40);

        background.add(importBtn);
        background.add(exportBtn);

        importBtn.addActionListener(e -> {
            frame.dispose();
            eingabeScreen("import");
        });

        exportBtn.addActionListener(e -> {
            frame.dispose();
            eingabeScreen("export");
        });

        frame.setVisible(true);
    }

    // Eingabe Screen mit Bild
    public static void eingabeScreen(String typ) {
        JFrame frame = new JFrame("FertigImportWebsite");
        frame.setSize(600, 400);

        JLabel background = new JLabel(new ImageIcon("FertigImportWebsite.png"));
        background.setLayout(null);
        frame.setContentPane(background);

        JTextField preisField = new JTextField();
        JTextField mengeField = new JTextField();

        preisField.setBounds(200, 120, 200, 30);
        mengeField.setBounds(200, 180, 200, 30);

        JButton okBtn = new JButton("OK");
        JButton zurückBtn = new JButton("Zurück");

        okBtn.setBounds(200, 240, 200, 30);
        zurückBtn.setBounds(200, 280, 200, 30);

        background.add(preisField);
        background.add(mengeField);
        background.add(okBtn);
        background.add(zurückBtn);

        okBtn.addActionListener(e -> {
            try {
                double preis = Double.parseDouble(preisField.getText());
                int menge = Integer.parseInt(mengeField.getText());

                double gesamt = preis * menge;

                if (typ.equals("import")) {
                    geld -= gesamt;
                    exportScreen("Import erfolgreich! Kosten: " + gesamt + "€");
                } else {
                    geld += gesamt;
                    exportScreen("Export erfolgreich! Einnahmen: " + gesamt + "€");
                }

                frame.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Fehler bei Eingabe!");
            }
        });

        zurückBtn.addActionListener(e -> {
            frame.dispose();
            menuScreen();
        });

        frame.setVisible(true);
    }

    // Ergebnis Screen mit Bild
    public static void exportScreen(String text) {
        JFrame frame = new JFrame("FertigexportWebsite");
        frame.setSize(600, 400);

        JLabel background = new JLabel(new ImageIcon("FertigexportWebsite.png"));
        background.setLayout(null);
        frame.setContentPane(background);

        JLabel label = new JLabel("<html>" + text + "<br>Geld: " + geld + "€</html>");
        label.setBounds(150, 150, 300, 50);

        JButton backBtn = new JButton("Zurück");
        backBtn.setBounds(200, 250, 200, 40);

        background.add(label);
        background.add(backBtn);

        backBtn.addActionListener(e -> {
            frame.dispose();
            menuScreen();
        });

        frame.setVisible(true);
    }
}

