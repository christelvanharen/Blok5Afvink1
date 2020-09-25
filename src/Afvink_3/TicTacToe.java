// Christel van Haren, BIN-2c
// Blok 5, afvinkopdracht 3, Tic-Tac-Toe

package Afvink_3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JPanel
{
    // Als de 0 hier een even getal is dan wordt er een X geplaatst,
    // en bij een oneven getal wordt er een O geplaatst.
    JButton[] buttons = new JButton[9];
    int alternate = 0;

    public TicTacToe()
    { // Hier word de lay-out geplaatst van 3x3
        setLayout(new GridLayout(3,3));
        initializebuttons();
    }

    public void initializebuttons()
    {
        // De knoppen 0 t/m 8 worden gevormd, dus 9 knoppen.
        for(int i = 0; i <= 8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].addActionListener(new buttonListener());
            add(buttons[i]);
        }
    }

    public void resetButtons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i].setText("");
        }
    }

    // Wanneer er op een knop geklikt, dan wordt ActionEvent
    // aangeroepen.
    private class buttonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // Als er op een knop geklikt wordt dan komt er een X of
            // O te staan.
            JButton buttonClicked = (JButton)e.getSource();
            if(alternate%2 == 0)
                buttonClicked.setText("X");
            else
                buttonClicked.setText("O");
            if(checkForWin())
            {
                JOptionPane.showConfirmDialog(null, "Je hebt " +
                        "gewonnen!");
                resetButtons();
            }
            alternate++;
        }

        public boolean checkForWin()
        {
            // Horizontaal winnen
            if( checkAdjacent(0,1) && checkAdjacent(1,2) )
                return true;
            else if( checkAdjacent(3,4) && checkAdjacent(4,5) )
                return true;
            else if ( checkAdjacent(6,7) && checkAdjacent(7,8))
                return true;

            // Verticaal winnen
            else if ( checkAdjacent(0,3) && checkAdjacent(3,6))
                return true;
            else if ( checkAdjacent(1,4) && checkAdjacent(4,7))
                return true;
            else if ( checkAdjacent(2,5) && checkAdjacent(5,8))
                return true;

            // Diagonaal winnen
            else if ( checkAdjacent(0,4) && checkAdjacent(4,8))
                return true;
            else return checkAdjacent(2, 4) && checkAdjacent(4, 6);
        }

        public boolean checkAdjacent(int a, int b)
        {
            return buttons[a].getText().equals(buttons[b].getText()) && !buttons[a].getText().equals("");
        }
    }

    public static void main(String[] args)
    {
        // New Frame maken
        JFrame window = new JFrame("TicTacToe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe());
        window.setBounds(300,300,300,300);
        window.setVisible(true);
    }
}