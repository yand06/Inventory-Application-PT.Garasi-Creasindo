package com.kkp.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import com.kkp.connection.ConnectDatabase;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.kkp.application.Application;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RegisterForm extends javax.swing.JPanel {

    private String user, pass;
    private List<String> userList = new ArrayList<>();
    private List<String> passList = new ArrayList<>();
    private javax.swing.JLabel lbBackToLogin;

    private Connection conn = new ConnectDatabase().connect();

    public RegisterForm() {
        initComponents();
        init();
        koneksiDatabase();
    }

    private void koneksiDatabase() {
        ConnectDatabase koneksiDatabase = new ConnectDatabase();
        conn = koneksiDatabase.connect();
        try
        {
            String query = "Select * From user;";
//          Menyiapkan statement SQL untuk dieksekusi dengan menggunakan objek Connection bernama con
            PreparedStatement st = conn.prepareStatement(query);
//          Mengeksekusi query SQL dan menyimpan hasilnya dalam objek ResulSet bernama rs
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                user = rs.getString("UserName");
                pass = rs.getString("Password");
                userList.add(user);
                passList.add(pass);
            }
        } catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registrasi() throws SQLException {
        try
        {
            ConnectDatabase koneksiDatabase = new ConnectDatabase();
            conn = koneksiDatabase.connect();

            String username = txtUser.getText();
            String password = txtPass.getText();
            for (int i = 0; i < userList.size(); i++)
            {
                if (username.equals(userList.get(i)) && password.equals(passList.get(i)))
                {
                    JOptionPane.showMessageDialog(null, "Username Sudah Ada!", "Error", JOptionPane.ERROR_MESSAGE);
                    clear();
                    return;
                }
            }

            if (txtUser.getText().isEmpty() || txtPass.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Inputan tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "INSERT INTO user (UserName, Password) VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, txtUser.getText());
            st.setString(2, txtPass.getText());

            int affectedRows = st.executeUpdate();
            if (affectedRows > 0)
            {
                Application.reloadLoginData();
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next())
                {
                    String idUser = rs.getString(1);
                    JOptionPane.showMessageDialog(null, "Registrasi Berhasil\nID Pengguna: " + idUser, "Sukses", JOptionPane.INFORMATION_MESSAGE);
                }
                clear();
                txtUser.requestFocus();
            } else
            {
                JOptionPane.showMessageDialog(null, "Registrasi Gagal", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e)
        {
            e.printStackTrace();
        }
    }

    private void init() {
        setLayout(new LoginFormLayout());
        register.setLayout(new LoginLayout());
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        register.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;"
                + "border:30,40,50,30");

        txtPass.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        cmdRegister.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");
        txtUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");

        lbBackToLogin = new JLabel();
        lbBackToLogin.setText("<html><u>Sudah Punya Akun?</u></html>");
        lbBackToLogin.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lbBackToLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBackToLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Mengubah cursor menjadi tangan (opsional)

        // Tambahkan ActionListener jika ingin label berfungsi sebagai tautan
        lbBackToLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBackToLoginMouseClicked(evt);
            }
        });

        // Tambahkan label ke panel 'login'
        register.add(lbBackToLogin);
    }

    // Tambahkan metode event handler untuk label "Register" (opsional)
    private void lbBackToLoginMouseClicked(java.awt.event.MouseEvent evt) {
        Application.reloadLoginData();
        LoginForm loginForm = new LoginForm();
        loginForm.setFocusToUser();
        Application.logout();
        clear();
    }

    public void setFocusToUser() {
        txtUser.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        register = new javax.swing.JPanel();
        cmdRegister = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();

        cmdRegister.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdRegister.setText("Sign Up");
        cmdRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRegisterActionPerformed(evt);
            }
        });
        cmdRegister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmdRegisterKeyPressed(evt);
            }
        });

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Sign Up");

        lbUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbUser.setText("Username");

        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
        });

        lbPass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbPass.setText("Password");

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(registerLayout.createSequentialGroup()
                        .addGroup(registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPass)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdRegister)
                            .addComponent(lbUser))
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(9, 9, 9)
                .addComponent(lbUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbPass)
                .addGap(5, 5, 5)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(cmdRegister)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(319, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRegisterActionPerformed
        try
        {
            registrasi();
        } catch (SQLException ex)
        {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdRegisterActionPerformed

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
        {
            txtPass.requestFocus();
        }
    }//GEN-LAST:event_txtUserKeyPressed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
        {
            cmdRegister.requestFocus();
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void clear() {
        txtUser.setText("");
        txtPass.setText("");
        txtUser.requestFocus();
    }
    private void cmdRegisterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmdRegisterKeyPressed
        try
        {
            registrasi();
        } catch (SQLException ex)
        {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdRegisterKeyPressed

    private class LoginFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock())
            {
                return new Dimension(0, 0);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock())
            {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock())
            {
                int width = parent.getWidth();
                int height = parent.getHeight();
                int loginWidth = UIScale.scale(320);
                int loginHeight = register.getPreferredSize().height;
                int x = (width - loginWidth) / 2;
                int y = (height - loginHeight) / 2;
                register.setBounds(x, y, loginWidth, loginHeight);
            }
        }
    }

    private class LoginLayout implements LayoutManager {

        private final int titleGap = 10;
        private final int textGap = 10;
        private final int labelGap = 5;
        private final int buttonGap = 50;

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock())
            {
                Insets insets = parent.getInsets();
                int height = insets.top + insets.bottom;

                height += lbTitle.getPreferredSize().height;
                height += UIScale.scale(titleGap);
                height += lbUser.getPreferredSize().height;
                height += UIScale.scale(labelGap);
                height += txtUser.getPreferredSize().height;
                height += UIScale.scale(textGap);

                height += lbPass.getPreferredSize().height;
                height += UIScale.scale(labelGap);
                height += txtPass.getPreferredSize().height;
                height += UIScale.scale(textGap); // Tambahkan gap untuk label "Register"
                height += lbBackToLogin.getPreferredSize().height;
                height += UIScale.scale(buttonGap - 20); // Sesuaikan gap jika diperlukan
                height += cmdRegister.getPreferredSize().height;
                return new Dimension(0, height);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock())
            {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock())
            {
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);

                lbTitle.setBounds(x, y, width, lbTitle.getPreferredSize().height);
                y += lbTitle.getPreferredSize().height + UIScale.scale(titleGap);

                lbUser.setBounds(x, y, width, lbUser.getPreferredSize().height);
                y += lbUser.getPreferredSize().height + UIScale.scale(labelGap);
                txtUser.setBounds(x, y, width, txtUser.getPreferredSize().height);
                y += txtUser.getPreferredSize().height + UIScale.scale(textGap);

                lbPass.setBounds(x, y, width, lbPass.getPreferredSize().height);
                y += lbPass.getPreferredSize().height + UIScale.scale(labelGap);
                txtPass.setBounds(x, y, width, txtPass.getPreferredSize().height);
                y += txtPass.getPreferredSize().height + UIScale.scale(textGap + 15); // Gap sebelum label "Register"

                cmdRegister.setBounds(x, y, width, cmdRegister.getPreferredSize().height);
                y += lbBackToLogin.getPreferredSize().height + UIScale.scale(buttonGap - 15); // Gap setelah label "Register"

                lbBackToLogin.setBounds(x, y, width, lbBackToLogin.getPreferredSize().height);
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdRegister;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel register;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
