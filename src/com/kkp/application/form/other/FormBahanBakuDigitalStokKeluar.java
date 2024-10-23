package com.kkp.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.kkp.connection.ConnectDatabase;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class FormBahanBakuDigitalStokKeluar extends javax.swing.JPanel {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Connection conn = new ConnectDatabase().connect();

    public FormBahanBakuDigitalStokKeluar() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        panelBorder.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Login.background;");
        panelHeader.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Panel.header;");
        txNo.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Login.background;");
        txTanggal.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Login.background;");
        txJk.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Login.background;");
        txUk.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Login.background;");
        txMu.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Login.background;");
        txJu.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: $Login.background;");
    }

    private void clear() {
        txNo.setText("");
        txUk.setText("");
        txJk.setText("");
        txJu.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        panelBorder = new com.kkp.component.PanelBorder();
        panelHeader = new com.kkp.component.PanelBorder();
        lbTambah = new javax.swing.JLabel();
        lbTambah1 = new javax.swing.JLabel();
        txNo = new com.kkp.component.TextField();
        lbTambah2 = new javax.swing.JLabel();
        lbTambah3 = new javax.swing.JLabel();
        txJk = new com.kkp.component.TextField();
        lbTambah4 = new javax.swing.JLabel();
        txUk = new com.kkp.component.TextField();
        lbTambah5 = new javax.swing.JLabel();
        txJu = new com.kkp.component.TextField();
        lbTambah6 = new javax.swing.JLabel();
        btnBatal = new com.kkp.component.Button();
        btnSubmit = new com.kkp.component.Button();
        txTanggal = new com.toedter.calendar.JDateChooser();
        txMu = new com.kkp.component.ComboBox();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Bahan Baku Digital Stok Keluar");

        lbTambah.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lbTambah.setForeground(new java.awt.Color(150, 150, 150));
        lbTambah.setText("Barang Keluar");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lbTambah)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTambah, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbTambah1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTambah1.setForeground(new java.awt.Color(200, 200, 200));
        lbTambah1.setText("Nomor");

        txNo.setForeground(new java.awt.Color(255, 255, 255));
        txNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lbTambah2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTambah2.setForeground(new java.awt.Color(200, 200, 200));
        lbTambah2.setText("Tanggal");

        lbTambah3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTambah3.setForeground(new java.awt.Color(200, 200, 200));
        lbTambah3.setText("Jenis Kertas");

        txJk.setForeground(new java.awt.Color(255, 255, 255));
        txJk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lbTambah4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTambah4.setForeground(new java.awt.Color(200, 200, 200));
        lbTambah4.setText("Ukuran");

        txUk.setForeground(new java.awt.Color(255, 255, 255));
        txUk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lbTambah5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTambah5.setForeground(new java.awt.Color(200, 200, 200));
        lbTambah5.setText("Mutasi");

        txJu.setForeground(new java.awt.Color(255, 255, 255));
        txJu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lbTambah6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTambah6.setForeground(new java.awt.Color(200, 200, 200));
        lbTambah6.setText("Jumlah");

        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setText("Batal");
        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit");
        btnSubmit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        txTanggal.setBackground(new java.awt.Color(102, 102, 102));
        txTanggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txTanggal.setForeground(new java.awt.Color(255, 255, 255));
        txTanggal.setDateFormatString(" MMMMM d, yyyy");
        txTanggal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txMu.setForeground(new java.awt.Color(255, 255, 255));
        txMu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Keluar" }));
        txMu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelBorderLayout = new javax.swing.GroupLayout(panelBorder);
        panelBorder.setLayout(panelBorderLayout);
        panelBorderLayout.setHorizontalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorderLayout.createSequentialGroup()
                .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorderLayout.createSequentialGroup()
                        .addContainerGap(634, Short.MAX_VALUE)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorderLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txMu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txJk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txUk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txJu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBorderLayout.createSequentialGroup()
                                .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTambah1)
                                    .addComponent(lbTambah2)
                                    .addComponent(lbTambah3)
                                    .addComponent(lbTambah4)
                                    .addComponent(lbTambah5)
                                    .addComponent(lbTambah6))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(50, 50, 50))
        );
        panelBorderLayout.setVerticalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTambah1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTambah2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTambah3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txJk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTambah4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txUk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTambah5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txMu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTambah6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txJu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(panelBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(panelBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        String sql = "insert into tabelbbd (no,tanggal,jenisKertas,ukuran,mutasi,qtyStock) values (?,?,?,?,?,?)";
        try
        {
            PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, txNo.getText());
            String date = dateFormat.format(txTanggal.getDate());
            stat.setString(2, date);
            stat.setString(3, txJk.getText());
            stat.setString(4, txUk.getText());
            stat.setString(5, txMu.getSelectedItem().toString());
            stat.setString(6, txJu.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Submit!");
            clear();
            txNo.requestFocus();
        } catch (HeadlessException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.kkp.component.Button btnBatal;
    private com.kkp.component.Button btnSubmit;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbTambah;
    private javax.swing.JLabel lbTambah1;
    private javax.swing.JLabel lbTambah2;
    private javax.swing.JLabel lbTambah3;
    private javax.swing.JLabel lbTambah4;
    private javax.swing.JLabel lbTambah5;
    private javax.swing.JLabel lbTambah6;
    private com.kkp.component.PanelBorder panelBorder;
    private com.kkp.component.PanelBorder panelHeader;
    private com.kkp.component.TextField txJk;
    private com.kkp.component.TextField txJu;
    private com.kkp.component.ComboBox txMu;
    private com.kkp.component.TextField txNo;
    private com.toedter.calendar.JDateChooser txTanggal;
    private com.kkp.component.TextField txUk;
    // End of variables declaration//GEN-END:variables
}
