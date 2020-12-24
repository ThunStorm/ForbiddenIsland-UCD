package com.esr.gui.tools;

import javax.swing.*;
import java.awt.*;

public class TwoLayeredIcon implements Icon {
    private final Icon top;
    private final Icon bottom;
    private int scaledX = 1;

    // Implement a wheel to draw multiple icons on a component
    public TwoLayeredIcon(Icon top, Icon bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    public TwoLayeredIcon(Icon top, Icon bottom, int scaledX) {
        this.top = top;
        this.bottom = bottom;
        this.scaledX = scaledX * 6;
    }

    public int getIconHeight() {
        return Math.max(top.getIconHeight(), bottom.getIconHeight());
    }

    public int getIconWidth() {
        return Math.max(top.getIconWidth(), bottom.getIconWidth());
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        bottom.paintIcon(c, g, x, y);
        top.paintIcon(c, g, x * scaledX, y);
    }
}

