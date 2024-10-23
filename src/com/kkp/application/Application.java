package com.kkp.application;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.kkp.application.form.LoginForm;
import com.kkp.application.form.MainForm;
import com.kkp.application.form.RegisterForm;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import raven.toast.Notifications;

public class Application extends javax.swing.JFrame {

    private static Application app;
    private final MainForm mainForm;
    private final LoginForm loginForm;
    private final RegisterForm registerForm;
    private final Dimension defaultSize = new Dimension(1470, 900);
    private boolean wasMaximized = true;

    public Application() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(defaultSize);
        setLocationRelativeTo(null);

        addWindowStateListener((WindowEvent e) ->
        {
            if (e.getOldState() == JFrame.MAXIMIZED_BOTH)
            {
                wasMaximized = true;
            } else if (e.getOldState() == JFrame.NORMAL)
            {
                wasMaximized = false;
            }

            switch (e.getNewState())
            {
                case JFrame.ICONIFIED:
                    break;
                case JFrame.NORMAL:
                    setSize(defaultSize);
                    setLocationRelativeTo(null);
                    break;
                case JFrame.MAXIMIZED_BOTH:
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                    break;
                default:
                    break;
            }
        });

        registerForm = new RegisterForm();
        mainForm = new MainForm();
        loginForm = new LoginForm();
        setContentPane(loginForm);
        Notifications.getInstance().setJFrame(this);
    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }

    // metode statik 'login' untuk proses login.
    public static void login() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.mainForm);
        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(app.mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    // metode statik 'logout' untuk proses logout.
    public static void logout() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.loginForm);
        app.loginForm.setFocusToUser();
        app.loginForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.loginForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    // Metode statik untuk menampilkan RegisterForm
    public static void showRegisterForm() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.registerForm);
        app.registerForm.setFocusToUser();
        app.registerForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.registerForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    // metode statik 'setSelectedMenu' untuk menetapkan menu terpilih
    public static void setSelectedMenu(int index, int subIndex) {
        app.mainForm.setSelectedMenu(index, subIndex);
    }

    public static void reloadLoginData() {
        app.loginForm.loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 14));
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(() ->
        {
            new com.kkp.splashscreen.SplashScreen(null, true).setVisible(true);
            app = new Application();
            //  app.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            app.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
