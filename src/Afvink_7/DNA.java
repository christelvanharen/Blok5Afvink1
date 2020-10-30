package Afvink_7;
/**
 * @author Jasper Versantvoort
 * class DNA
 */

import java.awt.*;

public class DNA {
    protected int gcpers;
    protected Color color;


    public void setgcpers(String seq) {
        /**
         * GC vormen door G en C te tellen daarna hier percentages van te maken
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
        /**
         * @return gc percentage als int
         */
        return gcpers;
    }

    public void setcolor(char a) {
        /**
         * kleur geven aan GC (rood) of AT(geel)
         */
        if (a == 'G' || a == 'C') {
            color = Color.RED;
        } else {
            color = Color.YELLOW;
        }
    }

    public Color getColor() {
        /**
         * @return kleur van nucleotide
         */
        return color;
    }
}