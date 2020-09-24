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
    {
        setLayout(new GridLayout(3,3));
        initializebuttons();
    }

    public void initializebuttons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].addActionListener(new buttonListener());

            add(buttons[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
            //because this whole class is a JPanel already
        }
    }

    public void resetButtons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i].setText("");
        }
    }

    // when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. There (and in this class), we decide what we want to do.
    private class buttonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            // Als er op een knop geklikt wordt en je kunt die
            JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
            if(alternate%2 == 0)
                buttonClicked.setText("X");
            else
                buttonClicked.setText("O");
            if(checkForWin())
            {
                JOptionPane.showConfirmDialog(null, "Game Over.");
                resetButtons();
            }
            alternate++;

        }

        public boolean checkForWin()
        {
            /**   Reference: the button array is arranged like this as the board
             *      0 | 1 | 2
             *      3 | 4 | 5
             *      6 | 7 | 8
             */
            //horizontal win check
            if( checkAdjacent(0,1) && checkAdjacent(1,2) ) //no need to put " == true" because the default check is for true
                return true;
            else if( checkAdjacent(3,4) && checkAdjacent(4,5) )
                return true;
            else if ( checkAdjacent(6,7) && checkAdjacent(7,8))
                return true;

                //vertical win check
            else if ( checkAdjacent(0,3) && checkAdjacent(3,6))
                return true;
            else if ( checkAdjacent(1,4) && checkAdjacent(4,7))
                return true;
            else if ( checkAdjacent(2,5) && checkAdjacent(5,8))
                return true;

                //diagonal win check
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
        JFrame window = new JFrame("Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe());
        window.setBounds(300,200,300,300);
        window.setVisible(true);
    }
}