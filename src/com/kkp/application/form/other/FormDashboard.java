package com.kkp.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.kkp.component.CustomCellRenderer;
import com.kkp.component.CustomHeaderRenderer;
import com.kkp.component.DataUpdateListener;
import com.kkp.connection.ConnectDatabase;
import com.kkp.model.Model_Card;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class FormDashboard extends javax.swing.JPanel implements DataUpdateListener {

    private final Connection conn = new ConnectDatabase().connect();
    private final int noColumnIndex = 0;
    private final int noColumnWidth = 50;

    public FormDashboard() {
        initComponents();
        setTableAction();
        setColumnWidth();
        tabelBBD.setGridColor(new Color(150, 150, 150));
        tabelBBP.setGridColor(new Color(150, 150, 150));
        panelBorder.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Menu.background;");
        tableBBD();
        tableBBP();
        tabelBBD.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelBBD.setDefaultRenderer(Object.class, new CustomCellRenderer());
        tabelBBP.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelBBP.setDefaultRenderer(Object.class, new CustomCellRenderer());
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        setCardValues();
        jScrollPaneMain.getVerticalScrollBar().setUnitIncrement(10);
    }

    public JTable getTabelBBD() {
        return tabelBBD;
    }

    public JTable getTabelBBP() {
        return tabelBBP;
    }

    private void setTableAction() {
        TableColumnModel columnModelBBD = tabelBBD.getColumnModel();
        columnModelBBD.getColumn(6).setCellRenderer(new ButtonPanelRenderer());
        columnModelBBD.getColumn(6).setCellEditor(new ButtonPanelEditor(this, tabelBBD));

        TableColumnModel columnModelBBP = tabelBBP.getColumnModel();
        columnModelBBP.getColumn(6).setCellRenderer(new ButtonPanelRenderer());
        columnModelBBP.getColumn(6).setCellEditor(new ButtonPanelEditor(this, tabelBBP));
    }

    static class ButtonPanelRenderer extends JPanel implements TableCellRenderer {

        private static final Color LIGHT_GRAY = new Color(168, 180, 191);
        private static final Color GRAY = new Color(148, 160, 171);
        private static final Color SELECTED_COLOR = new Color(158, 170, 181);

        private final JButton editButton;
        private final JButton deleteButton;

        public ButtonPanelRenderer() {
            setOpaque(true);
            editButton = new JButton(new ImageIcon("src/raven/icon/png/iconEdit.png"));
            deleteButton = new JButton(new ImageIcon("src/raven/icon/png/iconDelete.png"));
            editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            setLayout(new GridLayout(1, 2, 3, 5));
            add(editButton);
            add(deleteButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
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

            editButton.setBackground(getBackground());
            deleteButton.setBackground(getBackground());
            return this;
        }
    }

    static class ButtonPanelEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

        private static final Color LIGHT_GRAY = new Color(168, 180, 191);
        private static final Color GRAY = new Color(148, 160, 171);
        private static final Color SELECTED_COLOR = new Color(158, 170, 181);

        private JPanel panel;
        private JButton editButton;
        private JButton deleteButton;
        private JTable table;
        private int row;
        private FormDashboard dashboard;

        public ButtonPanelEditor(FormDashboard dashboard, JTable table) {
            this.dashboard = dashboard;
            this.table = table;
            panel = new JPanel(new GridLayout(1, 2, 3, 5));
            editButton = new JButton(new ImageIcon("src/raven/icon/png/iconEdit.png"));
            deleteButton = new JButton(new ImageIcon("src/raven/icon/png/iconDelete.png"));

            editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            editButton.addActionListener(e -> handleEditAction());
            deleteButton.addActionListener(e -> handleDeleteAction());

            panel.add(editButton);
            panel.add(deleteButton);
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row;
            String mutasi = table.getValueAt(row, 4).toString().toLowerCase();

            if ("masuk".equals(mutasi))
            {
                panel.setBackground(LIGHT_GRAY);
            } else if ("keluar".equals(mutasi))
            {
                panel.setBackground(GRAY);
            }

            if (isSelected)
            {
                panel.setBackground(SELECTED_COLOR);
            }

            return panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == editButton)
            {
                handleEditAction();

            } else if (e.getSource() == deleteButton)
            {
                handleDeleteAction();
            }
            fireEditingStopped();
        }

        private void handleEditAction() {
            int row = table.getSelectedRow();
            if (row != -1)
            {
                String nomor = table.getValueAt(row, 0).toString();
                Date tanggal;
                try
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    tanggal = dateFormat.parse(table.getValueAt(row, 1).toString());
                } catch (ParseException e)
                {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error parsing date : " + e.getMessage());
                    return;
                }
                String jenisKertas = table.getValueAt(row, 2).toString();
                String ukuran = table.getValueAt(row, 3).toString();
                String mutasi = table.getValueAt(row, 4).toString();
                int jumlah;
                try
                {
                    jumlah = Integer.parseInt(table.getValueAt(row, 5).toString());
                } catch (NumberFormatException e)
                {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error parsing jumlah : " + e.getMessage());
                    return;
                }
                if (table == dashboard.getTabelBBD())
                {
                    FormEditDataBBD formEdit = new FormEditDataBBD(nomor, tanggal, jenisKertas, ukuran, mutasi, jumlah);
                    formEdit.setDataUpdateListener(dashboard);
                    formEdit.setVisible(true);
                } else if (table == dashboard.getTabelBBP())
                {
                    FormEditDataBBP formEdit = new FormEditDataBBP(nomor, tanggal, jenisKertas, ukuran, mutasi, jumlah);
                    formEdit.setDataUpdateListener(dashboard);
                    formEdit.setVisible(true);
                }
            }
            fireEditingStopped();
        }

        private void handleDeleteAction() {
            int row = table.getSelectedRow();
            if (row != -1)
            {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION)
                {
                    String nomor = table.getValueAt(row, 0).toString();
                    String tableName = (table == dashboard.getTabelBBD()) ? "tabelbbd" : "tabelbbp";
                    try
                    {
                        Connection conn = new ConnectDatabase().connect();
                        String sql = "DELETE FROM " + tableName + " WHERE no=?";
                        PreparedStatement pst = conn.prepareStatement(sql);
                        pst.setString(1, nomor);
                        pst.executeUpdate();

                        // Stop editing before removing the row
                        if (table.isEditing())
                        {
                            table.getCellEditor().stopCellEditing();
                        }

                        // Remove the row from the table model
                        SwingUtilities.invokeLater(() ->
                        {
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.removeRow(row);
                            model.fireTableDataChanged();
                        });

                        JOptionPane.showMessageDialog(panel, "Record successfully deleted!");

                        // Refresh the dashboard data
                        SwingUtilities.invokeLater(() -> dashboard.refreshData());
                    } catch (HeadlessException | SQLException e)
                    {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "Error deleting record : " + e.getMessage());
                    }
                }
            }
            fireEditingStopped();
        }

    }

    @Override
    public void onDataUpdated() {
        refreshData();
    }

    private void setColumnWidth() {
        JTable[] tables =
        {
            tabelBBD, tabelBBP
        };
        for (JTable table : tables)
        {
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(noColumnIndex).setPreferredWidth(noColumnWidth);
            columnModel.getColumn(noColumnIndex).setMaxWidth(noColumnWidth);
            columnModel.getColumn(noColumnIndex).setMinWidth(noColumnWidth);
        }
    }

    private void tableBBD() {
        String sql = "SELECT * FROM tabelbbd ORDER BY no";
        DefaultTableModel model = (DefaultTableModel) tabelBBD.getModel();
        model.setRowCount(0);

        try
        {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next())
            {
                String a = hasil.getString("no");
                String b = hasil.getString("tanggal");
                String c = hasil.getString("jenisKertas");
                String d = hasil.getString("ukuran");
                String e = hasil.getString("mutasi");
                String f = hasil.getString("qtyStock");

                String[] data =
                {
                    a, b, c, d, e, f
                };
                model.addRow(data);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    private void tableBBP() {
        String sql = "SELECT * FROM tabelbbp ORDER BY no";
        DefaultTableModel model = (DefaultTableModel) tabelBBP.getModel();
        model.setRowCount(0);
        try
        {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next())
            {
                String a = hasil.getString("no");
                String b = hasil.getString("tanggal");
                String c = hasil.getString("jenisKertas");
                String d = hasil.getString("ukuran");
                String e = hasil.getString("mutasi");
                String f = hasil.getString("qtyStock");

                String[] data =
                {
                    a, b, c, d, e, f
                };
                model.addRow(data);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e);
        }

    }

    private void setCardValues() {
        String valuesBBD = calculateTotalBBD();
        String valuesBBP = calculateTotalBBP();
        String valuesBBMasuk = calculateTotalBBMasuk();
        String valuesBBKeluar = calculateTotalBBKeluar();

        // Set data for each card
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/raven/icon/png/iconBahanBakuDigital.png")), "BAHAN BAKU DIGITAL", valuesBBD, "Total Bahan Baku Digital."));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/raven/icon/png/iconBahanBakuProduksi.png")), "BAHAN BAKU PRODUKSI", valuesBBP, "Total Bahan Baku Produksi."));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/raven/icon/png/totalBarangMasuk.png")), "BAHAN BAKU MASUK", valuesBBMasuk, "Total Bahan Baku Digital & Produksi Masuk."));
        card4.setData(new Model_Card(new ImageIcon(getClass().getResource("/raven/icon/png/totalBarangKeluar.png")), "BAHAN BAKU KELUAR", valuesBBKeluar, "Total Bahan Baku Digital & Produksi Keluar."));
    }

    int total;

    private String calculateTotalBBD() {
        String query = "SELECT SUM(qtyStock) FROM tabelbbd";
        try
        {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(query);
            if (hasil.next())
            {
                total = hasil.getInt(1);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return String.valueOf(total);
    }

    private String calculateTotalBBP() {
        String query = "SELECT SUM(qtyStock) FROM tabelbbp";
        try
        {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(query);
            if (hasil.next())
            {
                total = hasil.getInt(1);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return String.valueOf(total);
    }

    int totalBBD = 0, totalBBP = 0, jumlah = 0;

    private String calculateTotalBBMasuk() {
        String queryBBD = "SELECT SUM(qtyStock) FROM tabelbbd WHERE mutasi='masuk'";
        String queryBBP = "SELECT SUM(qtyStock) FROM tabelbbp WHERE mutasi='masuk'";
        try
        {
            Statement statBBD = conn.createStatement();
            ResultSet hasilBBD = statBBD.executeQuery(queryBBD);
            if (hasilBBD.next())
            {
                totalBBD = hasilBBD.getInt(1);
            }
            hasilBBD.close();

            Statement statBBP = conn.createStatement();
            ResultSet hasilBBP = statBBP.executeQuery(queryBBP);
            if (hasilBBP.next())
            {
                totalBBP = hasilBBP.getInt(1);
            }
            hasilBBP.close();

            jumlah = totalBBD + totalBBP;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return String.valueOf(jumlah);
    }

    private String calculateTotalBBKeluar() {
        String queryBBD = "SELECT SUM(qtyStock) FROM tabelbbd WHERE mutasi='keluar'";
        String queryBBP = "SELECT SUM(qtyStock) FROM tabelbbp WHERE mutasi='keluar'";
        try
        {
            Statement statBBD = conn.createStatement();
            ResultSet hasilBBD = statBBD.executeQuery(queryBBD);
            if (hasilBBD.next())
            {
                totalBBD = hasilBBD.getInt(1);
            }
            hasilBBD.close();

            Statement statBBP = conn.createStatement();
            ResultSet hasilBBP = statBBP.executeQuery(queryBBP);
            if (hasilBBP.next())
            {
                totalBBP = hasilBBP.getInt(1);
            }
            hasilBBP.close();

            jumlah = totalBBD + totalBBP;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return String.valueOf(jumlah);
    }

    public void refreshData() {
        SwingUtilities.invokeLater(() ->
        {
            refreshTableBBD();
            refreshTableBBP();
            refreshCardValues();
        });
    }

    private void refreshTableBBD() {
        DefaultTableModel model = (DefaultTableModel) tabelBBD.getModel();
        model.setRowCount(0);
        tableBBD(); // This should populate the table with fresh data
        model.fireTableDataChanged();
    }

    private void refreshTableBBP() {
        DefaultTableModel model = (DefaultTableModel) tabelBBP.getModel();
        model.setRowCount(0);
        tableBBP(); // This should populate the table with fresh data
        model.fireTableDataChanged();
    }

    private void refreshCardValues() {
        setCardValues();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        panel = new javax.swing.JLayeredPane();
        card1 = new com.kkp.component.Card();
        card2 = new com.kkp.component.Card();
        card3 = new com.kkp.component.Card();
        card4 = new com.kkp.component.Card();
        jScrollPaneMain = new javax.swing.JScrollPane();
        panelBorder = new com.kkp.component.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPaneBBD = new javax.swing.JScrollPane();
        tabelBBD = new javax.swing.JTable();
        jScrollPaneBBP = new javax.swing.JScrollPane();
        tabelBBP = new javax.swing.JTable();
        btnSemuaMutasiBBD = new com.kkp.component.Button();
        btnMutasiMasukBBD = new com.kkp.component.Button();
        btnMutasiKeluarBBD = new com.kkp.component.Button();
        btnMutasiKeluarBBP = new com.kkp.component.Button();
        btnMutasiMasukBBP = new com.kkp.component.Button();
        btnSemuaMutasiBBP = new com.kkp.component.Button();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel1.setText("jLabel1");

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Dashboard");

        panel.setLayout(new java.awt.GridLayout(1, 0, 15, 0));
        panel.add(card1);
        panel.add(card2);
        panel.add(card3);
        panel.add(card4);

        panelBorder.setBackground(new java.awt.Color(128, 140, 151));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bahan Baku Digital");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Bahan Baku Produksi");

        tabelBBD.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        tabelBBD.setForeground(new java.awt.Color(255, 255, 255));
        tabelBBD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Jenis Kertas", "Ukuran", "Mutasi", "Jumlah", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelBBD.setPreferredSize(new java.awt.Dimension(300, 400));
        tabelBBD.setRowHeight(40);
        tabelBBD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBBDMouseClicked(evt);
            }
        });
        jScrollPaneBBD.setViewportView(tabelBBD);
        if (tabelBBD.getColumnModel().getColumnCount() > 0) {
            tabelBBD.getColumnModel().getColumn(6).setMinWidth(90);
            tabelBBD.getColumnModel().getColumn(6).setPreferredWidth(90);
            tabelBBD.getColumnModel().getColumn(6).setMaxWidth(90);
        }

        tabelBBP.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        tabelBBP.setForeground(new java.awt.Color(255, 255, 255));
        tabelBBP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Jenis Kertas", "Ukuran", "Mutasi", "Jumlah", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelBBP.setPreferredSize(new java.awt.Dimension(300, 400));
        tabelBBP.setRowHeight(40);
        tabelBBP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBBPMouseClicked(evt);
            }
        });
        jScrollPaneBBP.setViewportView(tabelBBP);
        if (tabelBBP.getColumnModel().getColumnCount() > 0) {
            tabelBBP.getColumnModel().getColumn(6).setMinWidth(90);
            tabelBBP.getColumnModel().getColumn(6).setPreferredWidth(90);
            tabelBBP.getColumnModel().getColumn(6).setMaxWidth(90);
        }

        btnSemuaMutasiBBD.setForeground(new java.awt.Color(255, 255, 255));
        btnSemuaMutasiBBD.setText("Semua Mutasi");
        btnSemuaMutasiBBD.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSemuaMutasiBBD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSemuaMutasiBBDMouseEntered(evt);
            }
        });
        btnSemuaMutasiBBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSemuaMutasiBBDActionPerformed(evt);
            }
        });

        btnMutasiMasukBBD.setForeground(new java.awt.Color(255, 255, 255));
        btnMutasiMasukBBD.setText("Mutasi Masuk");
        btnMutasiMasukBBD.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnMutasiMasukBBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMutasiMasukBBDActionPerformed(evt);
            }
        });

        btnMutasiKeluarBBD.setForeground(new java.awt.Color(255, 255, 255));
        btnMutasiKeluarBBD.setText("Mutasi Keluar");
        btnMutasiKeluarBBD.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnMutasiKeluarBBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMutasiKeluarBBDActionPerformed(evt);
            }
        });

        btnMutasiKeluarBBP.setForeground(new java.awt.Color(255, 255, 255));
        btnMutasiKeluarBBP.setText("Mutasi Keluar");
        btnMutasiKeluarBBP.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnMutasiKeluarBBP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMutasiKeluarBBPActionPerformed(evt);
            }
        });

        btnMutasiMasukBBP.setForeground(new java.awt.Color(255, 255, 255));
        btnMutasiMasukBBP.setText("Mutasi Masuk");
        btnMutasiMasukBBP.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnMutasiMasukBBP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMutasiMasukBBPActionPerformed(evt);
            }
        });

        btnSemuaMutasiBBP.setForeground(new java.awt.Color(255, 255, 255));
        btnSemuaMutasiBBP.setText("Semua Mutasi");
        btnSemuaMutasiBBP.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSemuaMutasiBBP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSemuaMutasiBBPActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(90, 92, 94));

        javax.swing.GroupLayout panelBorderLayout = new javax.swing.GroupLayout(panelBorder);
        panelBorder.setLayout(panelBorderLayout);
        panelBorderLayout.setHorizontalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorderLayout.createSequentialGroup()
                        .addComponent(btnSemuaMutasiBBP, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMutasiMasukBBP, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMutasiKeluarBBP, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBorderLayout.createSequentialGroup()
                        .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneBBP, javax.swing.GroupLayout.DEFAULT_SIZE, 1072, Short.MAX_VALUE)
                            .addGroup(panelBorderLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPaneBBD)
                            .addGroup(panelBorderLayout.createSequentialGroup()
                                .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(panelBorderLayout.createSequentialGroup()
                                        .addComponent(btnSemuaMutasiBBD, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnMutasiMasukBBD, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnMutasiKeluarBBD, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 661, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))))
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        panelBorderLayout.setVerticalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSemuaMutasiBBD, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMutasiMasukBBD, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMutasiKeluarBBD, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneBBD, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSemuaMutasiBBP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMutasiMasukBBP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMutasiKeluarBBP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneBBP, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        jScrollPaneMain.setViewportView(panelBorder);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneMain)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPaneMain)
                .addGap(1, 1, 1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSemuaMutasiBBDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSemuaMutasiBBDMouseEntered

    }//GEN-LAST:event_btnSemuaMutasiBBDMouseEntered

    private void btnSemuaMutasiBBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemuaMutasiBBDActionPerformed
        tableBBD();
    }//GEN-LAST:event_btnSemuaMutasiBBDActionPerformed

    private void btnSemuaMutasiBBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemuaMutasiBBPActionPerformed
        tableBBP();
    }//GEN-LAST:event_btnSemuaMutasiBBPActionPerformed

    private void btnMutasiMasukBBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMutasiMasukBBDActionPerformed
        String sql = "Select * From tabelbbd where mutasi='masuk'";
        DefaultTableModel model = (DefaultTableModel) tabelBBD.getModel();
        model.setRowCount(0);
        try
        {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next())
            {
                String a = hasil.getString("no");
                String b = hasil.getString("tanggal");
                String c = hasil.getString("jenisKertas");
                String d = hasil.getString("ukuran");
                String e = hasil.getString("mutasi");
                String f = hasil.getString("qtyStock");

                String[] data =
                {
                    a, b, c, d, e, f
                };
                model.addRow(data);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
    }//GEN-LAST:event_btnMutasiMasukBBDActionPerformed

    private void btnMutasiKeluarBBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMutasiKeluarBBDActionPerformed
        String sql = "Select * From tabelbbd where mutasi='keluar'";
        DefaultTableModel model = (DefaultTableModel) tabelBBD.getModel();
        model.setRowCount(0);
        try
        {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next())
            {
                String a = hasil.getString("no");
                String b = hasil.getString("tanggal");
                String c = hasil.getString("jenisKertas");
                String d = hasil.getString("ukuran");
                String e = hasil.getString("mutasi");
                String f = hasil.getString("qtyStock");

                String[] data =
                {
                    a, b, c, d, e, f
                };
                model.addRow(data);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
    }//GEN-LAST:event_btnMutasiKeluarBBDActionPerformed

    private void btnMutasiMasukBBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMutasiMasukBBPActionPerformed
        String sql = "Select * From tabelbbp where mutasi='masuk'";
        DefaultTableModel model = (DefaultTableModel) tabelBBP.getModel();
        model.setRowCount(0);
        try
        {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next())
            {
                String a = hasil.getString("no");
                String b = hasil.getString("tanggal");
                String c = hasil.getString("jenisKertas");
                String d = hasil.getString("ukuran");
                String e = hasil.getString("mutasi");
                String f = hasil.getString("qtyStock");

                String[] data =
                {
                    a, b, c, d, e, f
                };
                model.addRow(data);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
    }//GEN-LAST:event_btnMutasiMasukBBPActionPerformed

    private void btnMutasiKeluarBBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMutasiKeluarBBPActionPerformed
        String sql = "Select * From tabelbbp where mutasi='keluar'";
        DefaultTableModel model = (DefaultTableModel) tabelBBP.getModel();
        model.setRowCount(0);
        try
        {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next())
            {
                String a = hasil.getString("no");
                String b = hasil.getString("tanggal");
                String c = hasil.getString("jenisKertas");
                String d = hasil.getString("ukuran");
                String e = hasil.getString("mutasi");
                String f = hasil.getString("qtyStock");

                String[] data =
                {
                    a, b, c, d, e, f
                };
                model.addRow(data);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
    }//GEN-LAST:event_btnMutasiKeluarBBPActionPerformed

    private void tabelBBDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBBDMouseClicked

    }//GEN-LAST:event_tabelBBDMouseClicked

    private void tabelBBPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBBPMouseClicked

    }//GEN-LAST:event_tabelBBPMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.kkp.component.Button btnMutasiKeluarBBD;
    private com.kkp.component.Button btnMutasiKeluarBBP;
    private com.kkp.component.Button btnMutasiMasukBBD;
    private com.kkp.component.Button btnMutasiMasukBBP;
    private com.kkp.component.Button btnSemuaMutasiBBD;
    private com.kkp.component.Button btnSemuaMutasiBBP;
    private com.kkp.component.Card card1;
    private com.kkp.component.Card card2;
    private com.kkp.component.Card card3;
    private com.kkp.component.Card card4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPaneBBD;
    private javax.swing.JScrollPane jScrollPaneBBP;
    private javax.swing.JScrollPane jScrollPaneMain;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb;
    private javax.swing.JLayeredPane panel;
    private com.kkp.component.PanelBorder panelBorder;
    private javax.swing.JTable tabelBBD;
    private javax.swing.JTable tabelBBP;
    // End of variables declaration//GEN-END:variables

}
