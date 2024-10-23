package com.kkp.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class Button extends JButton {
    private boolean isHovered = false;

    public Button() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Menambahkan MouseListener untuk mendeteksi hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();  // Menggambar ulang tombol saat di-hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();  // Menggambar ulang tombol saat tidak di-hover
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth() - 1;
        int height = getHeight() - 1;

        // Menggunakan gradasi yang berbeda ketika tombol di-hover
        if (isHovered) {
            GradientPaint gradientHover = new GradientPaint(0, 0, new Color(19,29,38), 0, getHeight(), new Color(88, 100, 111));
            g2.setPaint(gradientHover);
        } else {
            GradientPaint gradientNormal = new GradientPaint(0, 0, new Color(39, 49, 58), 0, getHeight(), new Color(108, 120, 131));
            g2.setPaint(gradientNormal);
        }

        // Menggambar tombol dengan sudut membulat
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, height, height));
        g2.dispose();

        super.paintComponent(grphcs);
    }
}
