package com.kkp.component;

import javax.swing.*;
import java.awt.*;

public class ComboBox extends JComboBox<String> {

    private int cornerRadius;

    public ComboBox() {
        this(15); // Mengatur radius default
    }

    public ComboBox(int radius) {
        this.cornerRadius = radius;
        setOpaque(false); // Agar tidak menggunakan background default JComboBox

        // Menambahkan item ke ComboBox
        addItem("Masuk");
        addItem("Keluar");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Menggambar border dengan sudut melengkung
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
    }

    @Override
    public Insets getInsets() {
        int padding = cornerRadius / 2;
        return new Insets(padding, padding, padding, padding);
    }
}
