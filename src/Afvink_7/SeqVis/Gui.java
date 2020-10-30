package Afvink_7.SeqVis;

/**
 * @author: Christel van Haren
 * Blok 5, Afvinkopdracht 7: Sequentie visualiseren
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Gui {
    private JButton kiesEenBestandButton;
    private JButton visualiserenButton;
    private JTextField bestandskiezer;
    private JTextArea textArea;
    private JLabel label;
    private JPanel paneel;
    static final String[] ONE = { "R", "N", "D", "C", "Q", "E", "G",
            "H", "K", "S", "T", "Y", "A", "F", "I", "L", "M", "P", "W",
            "V"};
    private final JPanel tekenpaneel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Visualisatie");
        frame.setContentPane(new Gui().paneel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public Gui() {
        /**
         * Het tekenpaneel maken
         */
        tekenpaneel = new JPanel();
        tekenpaneel.setPreferredSize(new Dimension(500, 100));
        tekenpaneel.setBackground(Color.white);
        tekenpaneel.add(paneel);
        tekenpaneel.setVisible(true);

        kiesEenBestandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Het bestand kan worden geopend
                 */
                File selectedFile;
                JFileChooser fileChooser = new JFileChooser();
                int reply = fileChooser.showOpenDialog(null);
                if (reply == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    bestandskiezer.setText(((File) selectedFile).getAbsolutePath());
                }
            }
        });

        visualiserenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Controleert of het DNA, RNA of eiwit is
                 */
                int jeiwit = 0;
                int jDNA = 0;
                int jRNA = 0;
                String seq = null;

                String line;
                int t = 0;

                try {
                    BufferedReader inFile = new BufferedReader(new FileReader(bestandskiezer.getText()));
                    while ((line = inFile.readLine()) != null) {
                        if (seq == null) {
                            seq = line.toString();
                        } else {
                            seq += line.toString();
                        }
                        for (int i = 0; i < line.length(); i++) {
                            t++;

                            char a = line.charAt(i);
                            System.out.println(a);
                            if (a == 'A' || a == 'G' || a == 'T' || a == 'C') {
                                jDNA++;
                            }
                            if (a == 'A' || a == 'G' || a == 'U' || a == 'C') {
                                jRNA++;
                            }
                            for (int x = 0; x < ONE.length; x++) {
                                char s = ONE[x].charAt(0);
                                if (a == s) {
                                    jeiwit++;
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

                if (jDNA == t) {
                    textArea.setText("DNA: " + seq);
                    DNA d1 = new DNA();
                    assert seq != null;
                    d1.setgcpers(seq);
                    textArea.setText("Het GC-percentage is " + d1.getgcpers() + "%");
                    System.out.println(d1.getgcpers());

                    Graphics tekenveld = tekenpaneel.getGraphics();
                    int afstand = 50;
                    for (int i = 0; i < seq.length(); i++) {
                        char a = seq.charAt(i);
                        d1.setcolor(a);
                        tekenveld.setColor(d1.getColor());
                        tekenveld.drawRect(afstand, 50, 10, 20);
                        tekenveld.fillRect(afstand, 50, 10, 20);
                        afstand += 10;
                    }
                } else if (jRNA == t) {
                    textArea.setText("RNA: " + seq);
                    RNA r1 = new RNA();
                    Graphics tekenveld = tekenpaneel.getGraphics();
                    int afstand = 50;
                    for (int i = 0; i < seq.length(); i++) {
                        char a = seq.charAt(i);
                        r1.setcolor(a);
                        tekenveld.setColor(r1.getColor());
                        tekenveld.drawRect(afstand, 50, 10, 20);
                        tekenveld.fillRect(afstand, 50, 10, 20);
                        afstand += 10;
                    }
                } else if (jeiwit == t) {
                    textArea.setText("Eiwit: " + seq);
                    Eiwit p1 = new Eiwit();
                    Graphics tekenveld = tekenpaneel.getGraphics();
                    int afstand = 50;
                    for (int i = 0; i < seq.length(); i++) {
                        char a = seq.charAt(i);
                        p1.setcolor(a);
                        tekenveld.setColor(p1.getColor());
                        tekenveld.drawRect(afstand, 50, 10, 20);
                        tekenveld.fillRect(afstand, 50, 10, 20);
                        afstand += 10;
                    }

                } else {
                    textArea.setText("Er is een onjuiste sequentie " +
                            "ingevoerd");
                }

            }
        });
    }

}
