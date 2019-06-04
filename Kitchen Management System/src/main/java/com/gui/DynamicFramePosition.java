package com.gui;

import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class DynamicFramePosition {
	/**
	 * Calculate dynamically the max borders of a screen based on the resolution
	 * @param frame The frame to move
	 * @return The max bounds of the screen
	 */
	private static Rectangle getMaxWindowBounds(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
        bounds.x += insets.left;
        bounds.y += insets.top;
        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;
        return bounds;
    }

    public static void setLocationToTop(JFrame frame) {
        frame.setLocation(frame.getX(), getMaxWindowBounds(frame).y);
    }

    public static void setLocationToLeft(JFrame frame) {
        frame.setLocation(getMaxWindowBounds(frame).x, frame.getY());
    }

    public static void setLocationToBottom(JFrame frame) {
        Rectangle bounds = getMaxWindowBounds(frame);
        frame.setLocation(frame.getX(), bounds.y + bounds.height - frame.getHeight());
    }

    public static void setLocationToRight(JFrame frame) {
        Rectangle bounds = getMaxWindowBounds(frame);
        frame.setLocation(bounds.x + bounds.width - frame.getWidth(), frame.getY());
    }
}
