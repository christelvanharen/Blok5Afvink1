package Afvink_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Afvink_5 extends JFrame implements ActionListener {

    private JTextField textin;
    private JTextField textuit;
    private JLabel label;
    private JButton knop;


    public static void main(String[] args) {
        Afvink_5 frame = new Afvink_5();
        frame.setSize(500, 500);
        frame.createGui();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void createGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        label = new JLabel("Vul de eenletter code van de aminozuren in en krijg de 3 letterige code.");
        window.add(label);

        textin = new JTextField("1 lettercode", 40);
        window.add(textin);

        knop = new JButton("Translate");
        knop.addActionListener(this);
        window.add(knop);

        textuit = new JTextField("3 lettercode", 40);
        window.add(textuit);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        StringBuilder s1 = new StringBuilder();


        for (int i = 0; i < (textin.getText().length()); i++) {
            String c = Character.toString(textin.getText().charAt(i));
            try {
                if (i != textin.getText().length() - 1) {
                    s1.append(Translator.one2three(c.toUpperCase())).append("-");
                } else { // Wanneer het de laatste is geen streepje.
                    s1.append(Translator.one2three(c.toUpperCase()));
                }

            } catch (NotAnAA notAnAA) { // ongeldig aminozuur wordt aangegeven met popup
                JOptionPane.showMessageDialog(null, "bevat ongeldig aminozuur");
                notAnAA.printStackTrace();
                break;
            }
        }

        textuit.setText(String.valueOf(s1));
    }
}