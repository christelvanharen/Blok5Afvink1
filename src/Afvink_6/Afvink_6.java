package Afvink_6;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Christel van Haren
 * Blok 5, Afvinkopdracht 6: YANAARA
 */

public class Afvink_6 {
    private JPanel panel;
    private JButton bestand_inlezen;
    private JTextField pathway_bestand;
    private JButton Analyseren;
    private JTextArea textArea1;
    private JLabel bestand;
    private JLabel informatie;
    static final String[] ONE = { "R", "N", "D", "C", "Q", "E", "G",
            "H", "K", "S", "T", "Y", "A", "F", "I", "L", "M", "P", "W",
            "V"};
    static int polair = 0;
    static int apolair = 0;
    static int goed = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("YANAARA");
        frame.setContentPane(new Afvink_6().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Afvink_6() {
        bestand_inlezen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File selectedFile;
                JFileChooser fileChooser = new JFileChooser();
                int reply = fileChooser.showOpenDialog(null);
                if (reply == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    pathway_bestand.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        Analyseren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line;
                int t = 0;

                try {
                    BufferedReader inFile = new BufferedReader(new FileReader(pathway_bestand.getText()));
                    while ((line = inFile.readLine()) != null) {
                        for (int i = 0; i < line.length(); i++) {
                            t ++;
                            char a = line.charAt(i);
                            System.out.println(a);
                            for (int x = 0; x< ONE.length;x++) {
                                char s = ONE[x].charAt(0);
                                if (a == s ) {
                                    goed ++;
                                    if (x>11){
                                        polair ++;
                                    } else {
                                        apolair ++;
                                    }
                                }
                            }
                        }
                    }
                    System.out.println(polair);
                    inFile.close();

                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

                if (goed != t) {
                    System.out.println(goed);
                    textArea1.setText("Er zit een onjuist aminozuur " +
                            "in het bestand");
                }
                else{
                    textArea1.setText("Alle aminozuren zijn juist \n" +
                            "Het totaal aantal aminozuren zijn: " + (polair+apolair) + "\n" +
                            (polair*100/(polair+apolair)) + "% van de" +
                            " aminozuren zijn polair en " +
                            (apolair*100/(polair+apolair)) + "% zijn " +
                            "apolair.");
                }
            }

        });

    }}


