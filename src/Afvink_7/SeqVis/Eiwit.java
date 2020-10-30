package Afvink_7.SeqVis;

/**
 * @author: Christel van Haren
 * Eiwit class
 */

import java.awt.*;

public class Eiwit {
    protected Color color;
    static final String[] ONE = {"R", "N", "D", "C", "Q", "E", "G", "H", "K", "S", "T", "Y",
            "A", "F", "I", "L", "M", "P", "W", "V"};

    public void setcolor(char a) {
        /**
         * Een kleur meegeven aan polair (blauw) en apolair (rood)
         */
        for (int x = 0; x < ONE.length; x++) {
            char s = ONE[x].charAt(0);
            if (a == s) {
                if (x > 11) {
                    color = Color.blue;
                } else {
                    color = Color.RED;
                }
            }
        }
    }

    public Color getColor() {
        return color;
    }
}
