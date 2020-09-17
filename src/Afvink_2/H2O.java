package Afvink_2;// Christel van Haren, BIN-2c
// Blok 5, Afvinkopdracht 2, het watermolecuul tekenen

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class H2O extends JFrame implements ActionListener {
    private JTextField xtext;
    private JTextField ytext;
    private JLabel uitleg;
    private JLabel xlabel;
    private JLabel ylabel;
    private JButton button;
    private JPanel panel;

    // De GUI opzetten
    public static void main(String[] args) {
        H2O frame = new H2O();
        frame.setSize(330, 450);
        frame.createGui();
        frame.setVisible(true);
    }

    private void createGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        // De dimensie van het watermolecuul opzetten
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setBackground(Color.white);
        window.add(panel);

        // Uitleg bij het invoeren van de waardes
        uitleg = new JLabel("Voer bij x en y een waarde in van tussen" +
                " de 0 en 300");
        window.add(uitleg);

        // Het x-label aanmaken
        xlabel = new JLabel("x: ");
        window.add(xlabel);

        xtext = new JTextField(3);
        window.add(xtext);

        // Het y-label aanmaken
        ylabel = new JLabel("y: ");
        window.add(ylabel);

        ytext = new JTextField(3);
        window.add(ytext);

        // De knop aanmaken
        button = new JButton("Teken het watermolecuul");
        window.add(button);
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {

        // Zorgt ervoor dat knop zijn functie krijgt
        int x = Integer.parseInt(xtext.getText());
        int y = Integer.parseInt(ytext.getText());

        // De range van de x en de y waarde
        if (x > -1 & x < 301 & y > -1 & y < 301) {
            System.out.println(x);
            System.out.println(y);

            Graphics paper = panel.getGraphics();

            // De dimensie van het H20 molecuul
            paper.clearRect(0, 0, 300, 300);

            // De lijnen tussen de waterstofmoleculen en het
            // zuurstofmolecuul
            paper.drawLine(x - 60, y + 45, x + 15, y + 25);
            paper.drawLine(x - 60, y - 15, x + 15, y + 13);

            // De waterstofmoleculen
            paper.setColor(Color.blue);
            paper.fillOval(x - 75, (y + 30), 30, 30);
            paper.fillOval(x - 75, y - 30, 30, 30);

            // Het zuurstofmolecuul
            paper.setColor(Color.red);
            paper.fillOval(x, y, 45, 45);
        } else {
            //
            System.out.println("Er is geen correcte waarde ingevuld");
        }


    }
}