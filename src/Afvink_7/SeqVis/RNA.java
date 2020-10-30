package Afvink_7.SeqVis;

/**
 * @author: Christel van Haren
 * RNA class
 */

import java.awt.*;

public class RNA {
    protected Color color;
    public void setcolor(char a) {
        /**
         * Een kleur meegeven aan GC (rood), A (geel) en U (blauw)
         */
        if (a == 'G' || a == 'C') {
            color = Color.RED;
        } else if (a =='A'){
            color = Color.YELLOW;
        }
        else {
            color = Color.blue;
        }
    }

    public Color getColor() {
        return color;
    }
}
