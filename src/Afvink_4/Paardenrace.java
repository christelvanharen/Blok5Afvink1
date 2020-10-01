package Afvink_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paardenrace extends JPanel {

    JFrame frame;
    JPanel panel;
    JButton startButton;
    Paard friendlyHorse, enemyHorse;
    String friendlyHorseName, enemyHorseName;
    Graphics draw;
    int finishAfstand = 500;
    boolean finish, gameSet = false;
    int friendlyAfstand, enemyAfstand;

    public static void main(String[] args) {
        Paardenrace GUI = new Paardenrace();
    }

    public Paardenrace() {
        // Er wordt een nieuw frame gemaakt
        frame = new JFrame();
        frame.setSize(800, 400);
        frame.setBackground(Color.GRAY);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // De knop om de paardenrace mee te starten
        startButton = new JButton("Start de paardenrace");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameSet){
                    gameSetup();
                    runLoop();
                }
            }
        });
        c.gridx = 1;
        c.gridy = 0;
        frame.add(startButton, c);

        // Het frame waar de paardenrace in gaat plaatsvinden
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 300));
        panel.setBackground(Color.WHITE);
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        frame.add(panel, c);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void gameSetup() {
        // De namen van de paarden kunnen hier worden ingegeven
        draw = panel.getGraphics();
        friendlyHorseName = JOptionPane.showInputDialog(panel, "Naam van je paard?");
        enemyHorseName = JOptionPane.showInputDialog(panel, "Naam van je tegenstander?");

        friendlyHorse = new Paard(friendlyHorseName);
        enemyHorse = new Paard(enemyHorseName);
        panel.removeAll();
        panel.updateUI();
        gameSet = true;
    }

    public void endGame() {
        // Als de paardenrace is afgelopen dan wordt er een van de
        // volgende messages weergegeven.
        String winningMessage;
        if (friendlyHorse.getAfstand() >= finishAfstand && enemyHorse.getAfstand() >= finishAfstand) {
            JOptionPane.showMessageDialog(frame, "Het is gelijkspel!");
        } else if (friendlyHorse.getAfstand() >= finishAfstand && enemyHorse.getAfstand() < finishAfstand) {
            winningMessage = friendlyHorseName + " heeft de wedstrijd gewonnen!";
            JOptionPane.showMessageDialog(frame, winningMessage);
        } else if (enemyHorse.getAfstand() >= finishAfstand && friendlyHorse.getAfstand() < finishAfstand) {
            winningMessage = enemyHorseName + " heeft de wedstrijd gewonnen";
            JOptionPane.showMessageDialog(frame, winningMessage);
        }
    }

    public void pause(int mSec) {
        // Zorgt ervoor dat je de voortgang van de paarden kunt zien
        try {
            Thread.sleep(mSec);
        } catch (InterruptedException e) {
            System.out.println("De game staat op pauze");
        }
    }

    public void runLoop() {
        friendlyAfstand = friendlyHorse.getAfstand();
        enemyAfstand = enemyHorse.getAfstand();

        // Geeft de finishline weer
        draw.setColor(Color.BLACK);
        draw.drawLine(600, 90, 600, 240);
        draw.drawString(friendlyHorseName, 100, 90);
        draw.drawString(enemyHorseName, 100, 190);

        // Geeft de paarden weer
        while (!finish) {
            if (friendlyAfstand < finishAfstand && enemyAfstand < finishAfstand) {
                friendlyHorse.loop();
                enemyHorse.loop();
                friendlyAfstand = friendlyHorse.getAfstand();
                enemyAfstand = enemyHorse.getAfstand();
                draw.setColor(Color.ORANGE);
                draw.fillRect(100, 100, friendlyHorse.getAfstand(), 30);
                draw.setColor(Color.PINK);
                draw.fillRect(100, 200, enemyHorse.getAfstand(), 30);
                pause(20);
            } else {
                finish = true;
                gameSet = false;
            }
        }
        endGame();
    }
}