package Afvink_7;

/**
 * @author Jasper Versantvoort
 * class RNA
 */

import java.awt.*;

public class RNA {
    protected Color color;
    public void setcolor(char a) {
        /**
         * geeft kleur aan nucleotiden GC (rood), A(geel) en U (blauw)
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
        /**
         * @return de kleur horden bij nucleotiden
         */
        return color;
    }
}
