package Afvink_7;

/**
 * @author: Christel van Haren
 * Blok 5, Afvinkopdracht 7: SeqVis
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SeqVis {
    private JTextField bestandskiezer;
    private JButton bestandButton;
    private JButton analyseerButton;
    private JTextArea textArea1;
    private JLabel label;
    private JPanel panel;
    static final String[] ONE = {"R", "N", "D", "C", "Q", "E", "G", "H", "K", "S", "T", "Y",
            "A", "F", "I", "L", "M", "P", "W", "V"};
    private final JPanel tekenpanel;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Sequentie Visualisatie");
        frame.setContentPane(new SeqVis().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public SeqVis() {
        /**
         *
         */
        tekenpanel = new JPanel();
        tekenpanel.setPreferredSize(new Dimension(500, 100));
        tekenpanel.setBackground(Color.white);
        panel.add(tekenpanel);
        tekenpanel.setVisible(true);

        bestandButton.addActionListener(e -> {
            File selectedFile;
            JFileChooser fileChooser = new JFileChooser();
            int reply = fileChooser.showOpenDialog(null);
            if (reply == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                bestandskiezer.setText(selectedFile.getAbsolutePath());
            }
        });

        analyseerButton.addActionListener(e -> {
            /**
             *
             */
            int eiwit = 0;
            int DNA = 0;
            int RNA = 0;
            String seq = null;

            String line;
            int t = 0;

            try {
                BufferedReader inFile = new BufferedReader(new FileReader(bestandskiezer.getText()));
                while ((line = inFile.readLine()) != null) {
                    if (seq == null) {
                        seq = line;
                    } else {
                        seq += line;
                    }
                    for (int i = 0; i < line.length(); i++) {
                        t++;

                        char a = line.charAt(i);
                        System.out.println(a);
                        if (a == 'A' || a == 'G' || a == 'T' || a == 'C') {
                            DNA++;
                        }
                        if (a == 'A' || a == 'G' || a == 'U' || a == 'C') {
                            RNA++;
                        }
                        for (int x = 0; x < ONE.length; x++) {
                            char s = ONE[x].charAt(0);
                            if (a == s) {
                                eiwit++;
                                if (x > 11) {
                                } else {
                                }
                            }

                        }
                    }
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            if (DNA == t) {
                textArea1.setText("DNA sequentie: " + seq);
                DNA d1 = new DNA();
                d1.setgcpers(seq);
                System.out.println(d1.getgcpers());
                textArea1.setText("Het GC-percentage is: " + d1.getgcpers() + "%");

                Graphics tekenveld = tekenpanel.getGraphics();
                int afstand = 80;
                for (int i = 0; i < seq.length(); i++) {
                    char a = seq.charAt(i);
                    d1.setcolor(a);
                    tekenveld.setColor(d1.getColor());
                    tekenveld.drawRect(afstand, 80, 10, 20);
                    tekenveld.fillRect(afstand, 80, 10, 20);
                    afstand += 10;
                }
            } else if (RNA == t) {
                textArea1.setText("RNA sequentie: " + seq);
                RNA r1 = new RNA();
                Graphics tekenveld = tekenpanel.getGraphics();
                int afstand = 80;
                for (int i = 0; i < seq.length(); i++) {
                    char a = seq.charAt(i);
                    r1.setcolor(a);
                    tekenveld.setColor(r1.getColor());
                    tekenveld.drawRect(afstand, 80, 10, 20);
                    tekenveld.fillRect(afstand, 80, 10, 20);
                    afstand += 10;
                }
            } else if (eiwit == t) {
                textArea1.setText("Eiwitsequentie: " + seq);
                Eiwit p1 = new Eiwit();
                Graphics tekenveld = tekenpanel.getGraphics();
                int afstand = 80;
                for (int i = 0; i < seq.length(); i++) {
                    char a = seq.charAt(i);
                    p1.setcolor(a);
                    tekenveld.setColor(p1.getColor());
                    tekenveld.drawRect(afstand, 80, 10, 20);
                    tekenveld.fillRect(afstand, 80, 10, 20);
                    afstand += 10;
                }

            } else {
                textArea1.setText("Er is een onjuiste sequentie " +
                        "ingevoerd.");
            }

        });
    }

}

