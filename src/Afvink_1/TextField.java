package Afvink_1;

import javax.swing.*;

public class TextField {
    public static void main(String[] args) {

        JFrame f = new JFrame("Geef een input");

        JTextField demoTextField = new JTextField("Vul hier je input in", 20);
        demoTextField.setBounds(50, 100, 200, 25);

        JLabel demoLabel = new JLabel("Input:");
        demoLabel.setBounds(50, 80, 200, 25);

        f.add(demoTextField);f.add(demoLabel);
        f.setSize(300, 300);
        f.setLayout(null);
        f.setVisible(true);
    }
}