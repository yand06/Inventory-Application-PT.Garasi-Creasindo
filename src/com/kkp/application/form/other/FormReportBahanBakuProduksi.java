package com.kkp.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.kkp.component.CustomCellRenderer;
import com.kkp.component.CustomHeaderRenderer;
import com.kkp.connection.ConnectDatabase;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FormReportBahanBakuProduksi extends javax.swing.JPanel {

    private Connection conn = new ConnectDatabase().connect();
    private final int noColumnIndex = 0;
    private final int noColumnWidth = 50;

    public FormReportBahanBakuProduksi() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        panelBorder.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Login.background;");
        panelHeader.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Panel.header;");
        panelBorder1.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Login.background;");
        panelHeader1.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Panel.header;");
        panelBorder2.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Login.background;");
        panelHeader2.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Panel.header;");
        lbTambah2.setText("Bahan Baku Produksi  - Stok Keluar");
        scrollLaporanBBP.getVerticalScrollBar().setUnitIncrement(10);
        tableBBP();
        tableBBPMasuk();
        tableBBPKeluar();
        setColumnWidth();
        tabelBBPMasukKeluar.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelBBPMasukKeluar.setDefaultRenderer(Object.class, new CustomCellRenderer());
        tabelBBPMasuk.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelBBPMasuk.setDefaultRenderer(Object.class, new CustomCellRenderer());
        tabelBBPKeluar.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelBBPKeluar.setDefaultRenderer(Object.class, new CustomCellRenderer());
        tabelBBPMasukKeluar.setGridColor(new Color(150, 150, 150));
        tabelBBPMasuk.setGridColor(new Color(150, 150, 150));
        tabelBBPKeluar.setGridColor(new Color(150, 150, 150));
    }

    private void setColumnWidth() {
        // Array berisi tabel yang akan diatur lebar kolomnya
        JTable[] tables =
        {
            tabelBBPMasukKeluar, tabelBBPMasuk, tabelBBPKeluar
        };

        // Mengatur lebar kolom untuk setiap tabel
        for (JTable table : tables)
        {
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(noColumnIndex).setPreferredWidth(noColumnWidth);
            columnModel.getColumn(noColumnIndex).setMaxWidth(noColumnWidth);
            columnModel.getColumn(noColumnIndex).setMinWidth(noColumnWidth);
        }
    }

    private void tableBBP() {
        String sql = "Select * From tabelbbp";
        DefaultTableModel model = (DefaultTableModel) tabelBBPMasukKeluar.getModel();
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

    private void tableBBPMasuk() {
        String sql = "Select * From tabelbbp where mutasi='masuk'";
        DefaultTableModel model = (DefaultTableModel) tabelBBPMasuk.getModel();
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

    private void tableBBPKeluar() {
        String sql = "Select * From tabelbbp where mutasi='keluar'";
        DefaultTableModel model = (DefaultTableModel) tabelBBPKeluar.getModel();
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

    public void showReport(Connection connection, String reportPath) {
        try
        {
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Map<String, Object> parameters = new HashMap<String, Object>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            viewer.setVisible(true);
        } catch (JRException e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        scrollLaporanBBP = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        panelBorder = new com.kkp.component.PanelBorder();
        panelHeader = new com.kkp.component.PanelBorder();
        lbTambah = new javax.swing.JLabel();
        btnSubmitMasukKeluar = new com.kkp.component.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBBPMasukKeluar = new javax.swing.JTable();
        panelBorder1 = new com.kkp.component.PanelBorder();
        panelHeader1 = new com.kkp.component.PanelBorder();
        lbTambah1 = new javax.swing.JLabel();
        btnSubmitMasuk = new com.kkp.component.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelBBPMasuk = new javax.swing.JTable();
        panelBorder2 = new com.kkp.component.PanelBorder();
        panelHeader2 = new com.kkp.component.PanelBorder();
        lbTambah2 = new javax.swing.JLabel();
        btnSubmitKeluar = new com.kkp.component.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelBBPKeluar = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1290, 868));

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Laporan Bahan Baku Produksi");

        lbTambah.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lbTambah.setForeground(new java.awt.Color(150, 150, 150));
        lbTambah.setText("Bahan Baku Produksi  - Stok Masuk & Keluar");

        btnSubmitMasukKeluar.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmitMasukKeluar.setText("Print");
        btnSubmitMasukKeluar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSubmitMasukKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitMasukKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lbTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSubmitMasukKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubmitMasukKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabelBBPMasukKeluar.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        tabelBBPMasukKeluar.setForeground(new java.awt.Color(255, 255, 255));
        tabelBBPMasukKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Jenis Kertas", "Ukuran", "Mutasi", "Jumlah"
            }
        ));
        tabelBBPMasukKeluar.setPreferredSize(new java.awt.Dimension(300, 500));
        tabelBBPMasukKeluar.setRowHeight(35);
        jScrollPane1.setViewportView(tabelBBPMasukKeluar);

        javax.swing.GroupLayout panelBorderLayout = new javax.swing.GroupLayout(panelBorder);
        panelBorder.setLayout(panelBorderLayout);
        panelBorderLayout.setHorizontalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(10, 10, 10))
        );
        panelBorderLayout.setVerticalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        lbTambah1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lbTambah1.setForeground(new java.awt.Color(150, 150, 150));
        lbTambah1.setText("Bahan Baku Produksi  - Stok Masuk");

        btnSubmitMasuk.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmitMasuk.setText("Print");
        btnSubmitMasuk.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSubmitMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitMasukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHeader1Layout = new javax.swing.GroupLayout(panelHeader1);
        panelHeader1.setLayout(panelHeader1Layout);
        panelHeader1Layout.setHorizontalGroup(
            panelHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeader1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lbTambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 720, Short.MAX_VALUE)
                .addComponent(btnSubmitMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        panelHeader1Layout.setVerticalGroup(
            panelHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeader1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTambah1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubmitMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabelBBPMasuk.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        tabelBBPMasuk.setForeground(new java.awt.Color(255, 255, 255));
        tabelBBPMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Jenis Kertas", "Ukuran", "Mutasi", "Jumlah"
            }
        ));
        tabelBBPMasuk.setPreferredSize(new java.awt.Dimension(300, 500));
        tabelBBPMasuk.setRowHeight(35);
        jScrollPane3.setViewportView(tabelBBPMasuk);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(10, 10, 10))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panelHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        lbTambah2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lbTambah2.setForeground(new java.awt.Color(150, 150, 150));
        lbTambah2.setText("Bahan Baku Produksi  - Stok Masuk & Keluar");

        btnSubmitKeluar.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmitKeluar.setText("Print");
        btnSubmitKeluar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSubmitKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHeader2Layout = new javax.swing.GroupLayout(panelHeader2);
        panelHeader2.setLayout(panelHeader2Layout);
        panelHeader2Layout.setHorizontalGroup(
            panelHeader2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeader2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lbTambah2, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 720, Short.MAX_VALUE)
                .addComponent(btnSubmitKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        panelHeader2Layout.setVerticalGroup(
            panelHeader2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeader2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHeader2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTambah2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubmitKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabelBBPKeluar.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        tabelBBPKeluar.setForeground(new java.awt.Color(255, 255, 255));
        tabelBBPKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Jenis Kertas", "Ukuran", "Mutasi", "Jumlah"
            }
        ));
        tabelBBPKeluar.setPreferredSize(new java.awt.Dimension(300, 500));
        tabelBBPKeluar.setRowHeight(35);
        jScrollPane4.setViewportView(tabelBBPKeluar);

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(10, 10, 10))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panelHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        scrollLaporanBBP.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
            .addComponent(scrollLaporanBBP, javax.swing.GroupLayout.DEFAULT_SIZE, 1534, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(scrollLaporanBBP, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitMasukKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitMasukKeluarActionPerformed
        showReport(conn, "src/com/kkp/report/LaporanBBP.jrxml");
    }//GEN-LAST:event_btnSubmitMasukKeluarActionPerformed

    private void btnSubmitMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitMasukActionPerformed
        showReport(conn, "src/com/kkp/report/LaporanBBPMasuk.jrxml");
    }//GEN-LAST:event_btnSubmitMasukActionPerformed

    private void btnSubmitKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitKeluarActionPerformed
        showReport(conn, "src/com/kkp/report/LaporanBBPKeluar.jrxml");
    }//GEN-LAST:event_btnSubmitKeluarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.kkp.component.Button btnSubmitKeluar;
    private com.kkp.component.Button btnSubmitMasuk;
    private com.kkp.component.Button btnSubmitMasukKeluar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbTambah;
    private javax.swing.JLabel lbTambah1;
    private javax.swing.JLabel lbTambah2;
    private javax.swing.JPanel panel;
    private com.kkp.component.PanelBorder panelBorder;
    private com.kkp.component.PanelBorder panelBorder1;
    private com.kkp.component.PanelBorder panelBorder2;
    private com.kkp.component.PanelBorder panelHeader;
    private com.kkp.component.PanelBorder panelHeader1;
    private com.kkp.component.PanelBorder panelHeader2;
    private javax.swing.JScrollPane scrollLaporanBBP;
    private javax.swing.JTable tabelBBPKeluar;
    private javax.swing.JTable tabelBBPMasuk;
    private javax.swing.JTable tabelBBPMasukKeluar;
    // End of variables declaration//GEN-END:variables
}
