package com.kkp.component;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomHeaderRenderer extends DefaultTableCellRenderer {

    public CustomHeaderRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // Mengubah value menjadi uppercase jika value adalah String
        if (value != null)
        {
            value = value.toString().toUpperCase();
        }

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBackground(new Color(39, 49, 58)); // Warna latar belakang header
        setForeground(Color.WHITE); // Warna teks header
        return this;
    }
}
