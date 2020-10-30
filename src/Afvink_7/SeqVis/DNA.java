package Afvink_7.SeqVis;
/**
 * @author: Christel van Haren
 * DNA eiwit
 */

import java.awt.*;

public class DNA {
    protected int gcpers;
    protected Color color;


    public void setgcpers(String seq) {
        /**
         * Het GC% berekenen
         */
        int gc = 0;
        for (int i = 0; i < seq.length(); i++) {
            char a = seq.charAt(i);
            if (a == 'G' || a == 'C') {
                gc++;
            }
        }
        gcpers = 100 * gc / seq.length();
    }

    public int getgcpers() {
        return gcpers;
    }

    public void setcolor(char a) {
        /**
         * Een kleur meegeven aan GC (rood) of AT (geel)
         */
        if (a == 'G' || a == 'C') {
            color = Color.RED;
        } else {
            color = Color.YELLOW;
        }
    }

    public Color getColor() {
        return color;
    }
}