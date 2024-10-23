package com.kkp.component;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomCellRenderer extends DefaultTableCellRenderer {

    private static final Color LIGHT_GRAY = new Color(168, 180, 191);
    private static final Color GRAY = new Color(148, 160, 171);
    private static final Color SELECTED_COLOR = new Color(158, 170, 181);
    private static final Color TEXT_COLOR = new Color(39, 49, 58);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setOpaque(true);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 2, table.getGridColor()));
        setHorizontalAlignment(JLabel.CENTER);
        setForeground(TEXT_COLOR);

        String mutasi = table.getValueAt(row, 4).toString().toLowerCase();

        if ("masuk".equals(mutasi))
        {
            setBackground(LIGHT_GRAY);
        } else if ("keluar".equals(mutasi))
        {
            setBackground(GRAY);
        }

        if (isSelected)
        {
            setBackground(SELECTED_COLOR);
        }

        return this;
    }
}
