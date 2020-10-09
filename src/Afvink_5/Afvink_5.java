package Afvink_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Afvink_5 extends JFrame implements ActionListener {

    private JTextField textin;
    private JTextField textuit;

    public static void main(String[] args) {
        Afvink_5 frame = new Afvink_5();
        frame.setSize(400, 250);
        frame.createGui();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void createGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        // Maakt een label aan
        JLabel label = new JLabel("Vul de 1-lettercode van een aminozuur in");
        window.add(label);

        // Maakt TextField 1 aan voor de 1-lettercode
        textin = new JTextField("", 30);
        window.add(textin);

        // Maakt de vertaal knop aan
        JButton knop = new JButton("Translate");
        knop.addActionListener(this);
        window.add(knop);

        // Maakt Textfield 2 aan waar de vertaling in komt te staan
        textuit = new JTextField("", 30);
        window.add(textuit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder s1 = new StringBuilder();

        for (int i = 0; i < (textin.getText().length()); i++) {
            String c = Character.toString(textin.getText().charAt(i));

            // Laat een streepje zien tussen de aminozuren, behalve
            // bij de laatste.
            try {
                if (i != textin.getText().length() - 1) {
                    s1.append(Translator.one2three(c.toUpperCase())).append("-");
                } else {
                    s1.append(Translator.one2three(c.toUpperCase()));
                }

            // Laat een nieuw dialoog zien als er een onjuist
                // aminozuur is ingevoerd.
            } catch (NotAnAA notAnAA) {
                JOptionPane.showMessageDialog(null, "Er is geen " +
                        "juist aminozuur ingevoerd");
                notAnAA.printStackTrace();
                break;
            }
        }
        textuit.setText(String.valueOf(s1));
    }
}