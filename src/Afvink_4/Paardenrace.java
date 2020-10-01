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
    int finishAfstand = 600;
    boolean finish, gameSet = false;
    int friendlyAfstand, enemyAfstand;

    public static void main(String[] args) {
        Paardenrace GUI = new Paardenrace();
    }

    public Paardenrace() {

        // Construct GUI frame
        frame = new JFrame();
        frame.setSize(1000, 400);
        frame.setBackground(Color.GRAY);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //natural height, maximum width
        c.fill = GridBagConstraints.HORIZONTAL;

        startButton = new JButton("Start de paardenrace");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameSet) {
                    gameSetup();
                    runLoop();
                }
            }
        });
        c.gridx = 1;
        c.gridy = 0;
        frame.add(startButton, c);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(900, 300));
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
        draw = panel.getGraphics();
        // User input
        friendlyHorseName = JOptionPane.showInputDialog(panel, "Naam van je paard?");
        enemyHorseName = JOptionPane.showInputDialog(panel, "Naam van je tegenstander?");

        // Construct paard(en)
        friendlyHorse = new Paard(friendlyHorseName);
        enemyHorse = new Paard(enemyHorseName);

        panel.removeAll();
        panel.updateUI();

        gameSet = true;
    }

    public void endGame() {
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
        try {
            Thread.sleep(mSec);
        } catch (InterruptedException e) {
            System.out.println("Pauze intteruptie");
        }
    }

    public void runLoop() {
        friendlyAfstand = friendlyHorse.getAfstand();
        enemyAfstand = enemyHorse.getAfstand();

        draw.setColor(Color.BLACK);
        draw.drawLine(700, 90, 700, 240);
        draw.drawString(friendlyHorseName, 100, 90);
        draw.drawString(enemyHorseName, 100, 190);

        while (!finish) {
            if (friendlyAfstand < finishAfstand && enemyAfstand < finishAfstand) {
                friendlyHorse.loop();
                enemyHorse.loop();
                friendlyAfstand = friendlyHorse.getAfstand();
                enemyAfstand = enemyHorse.getAfstand();
                draw.setColor(Color.BLUE);
                draw.fillRect(100, 100, friendlyHorse.getAfstand(), 30);
                draw.setColor(Color.RED);
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