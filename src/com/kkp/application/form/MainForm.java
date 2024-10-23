package com.kkp.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.kkp.application.Application;
import com.kkp.application.form.other.FormBahanBakuProduksiStokKeluar;
import com.kkp.application.form.other.FormBahanBakuProduksiStokMasuk;
import com.kkp.application.form.other.FormDashboard;
import com.kkp.application.form.other.FormBahanBakuDigitalStokMasuk;
import com.kkp.application.form.other.FormBahanBakuDigitalStokKeluar;
import com.kkp.application.form.other.FormReportBahanBakuDigital;
import com.kkp.application.form.other.FormReportBahanBakuProduksi;
import com.kkp.menu.Menu;
import com.kkp.menu.MenuAction;
import javax.swing.JOptionPane;

public class MainForm extends JLayeredPane {

    public MainForm() {
        init();
    }

    private void init() {
//      menyetel border dengan EmptyBorder (5 pixel di setiap sisi)
        setBorder(new EmptyBorder(5, 5, 5, 5));
//      menyetel tata letak menggunakan MainFormLayout
        setLayout(new MainFormLayout());
//      membuat instace menu baru
        menu = new Menu();
//      membuat JPanel baru dengan tata letak Border Layout
        panelBody = new JPanel(new BorderLayout());
//      menginisialisasi ikon panah untuk menu
        initMenuArrowIcon();
//      menyetel properti gaya untuk menuButton
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
//      menambahkan ActionListener ke menuButton
        menuButton.addActionListener((ActionEvent e) ->
        {
            setMenuFull(!menu.isMenuFull());
        });
//      menginisialisasi event untuk menu
        initMenuEvent();
//      menyetel layar untuk menuButton ke POPUP_LAYER
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
//      menambahkan menuButton,menu, dan panelBody ke form
        add(menuButton);
        add(menu);
        add(panelBody);
    }

    /*
  * Mengatur orientasi komponen dan ikon panah menu.
  * Jika orientasi dari komponen adalah dari kiri ke kanan,
  * ikon akan diatur ke panah kiri, dan sebaliknya.
     */
    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        initMenuArrowIcon();
    }

    /*
  * Inisialisasi ikon panah menu berdasarkan orientasi komponen.
  * Jika tombol menu belum ada, dibuatkan tombol baru.
  * Ikon yang sesuai dipilih berdasarkan orientasi komponen.
     */
    private void initMenuArrowIcon() {
        if (menuButton == null)
        {
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("raven/icon/svg/" + icon, 0.8f));
    }

    /*
  * Inisialisasi event untuk menu.
  * Method ini menentukan aksi yang akan diambil berdasarkan index dan subIndex menu yang dipilih.
     */
    private void initMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) ->
        {
            // Application.mainForm.showForm(new DefaultForm("Form : " + index + " " + subIndex));
//          menampilkan form yang sesuai berdasarkan index dan subIndex menu
            switch (index)
            {
                case 0:
                    Application.showForm(new FormDashboard());
                    break;
                case 1:
                    switch (subIndex)
                    {
                        case 1:
                            Application.showForm(new FormBahanBakuDigitalStokMasuk());
                            break;
                        case 2:
                            Application.showForm(new FormBahanBakuDigitalStokKeluar());
                            break;
                        default:
                            action.cancel();
                            break;
                    }
                    break;
                case 2:
                    switch (subIndex)
                    {
                        case 1:
                            Application.showForm(new FormBahanBakuProduksiStokMasuk());
                            break;
                        case 2:
                            Application.showForm(new FormBahanBakuProduksiStokKeluar());
                            break;
                        default:
                            action.cancel();
                            break;
                    }
                    break;
                case 3:
                    switch (subIndex)
                    {
                        case 1:
                            Application.showForm(new FormReportBahanBakuDigital());
                            break;
                        case 2:
                            Application.showForm(new FormReportBahanBakuProduksi());
                            break;
                        default:
                            action.cancel();
                            break;
                    }
                    break;
                case 4:
                    int response = JOptionPane.showConfirmDialog(null,
                            "Apakah anda yakin ingin logout?",
                            "Konfirmasi Logout",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION)
                    {
                        Application.logout();
                    } else if (response == JOptionPane.NO_OPTION)
                    {
                        action.cancel();
                    }
                    break;
                default:
                    break;
            }
        });
    }

    /*
  * Mengatur tampilan menu penuh atau terlipat.
  * Jika orientasi dari komponen adalah dari kiri ke kanan,
  * ikon akan diatur ke arah kiri atau kanan tergantung dari parameter "full".
  * Jika orientasi dari komponen adalah dari kanan ke kiri,
  * ikon akan diatur ke arah kanan atau kiri tergantung dari parameter "full".
  * Selain itu, metode juga memperbarui tampilan menu dan melakukan revalidasi.
     */
    private void setMenuFull(boolean full) {
        String icon;
        if (getComponentOrientation().isLeftToRight())
        {
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else
        {
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("raven/icon/svg/" + icon, 0.8f));
        menu.setMenuFull(full);
        revalidate();
    }

//  metode untuk menyembunyikan menu
    public void hideMenu() {
        menu.hideMenuItem();
    }

    /*
  * Menampilkan suatu komponen di panelBody.
  * Menghapus semua komponen yang ada di panelBody,
  * kemudian menambahkan komponen yang diberikan,
  * dan melakukan repaint serta revalidasi panelBody.
     */
    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    /*
  * Mengatur menu yang dipilih.
  * Mengatur menu utama dan sub-menu yang dipilih berdasarkan index yang diberikan.
     */
    public void setSelectedMenu(int index, int subIndex) {
        menu.setSelectedMenu(index, subIndex);
    }

    private Menu menu;
    private JPanel panelBody;
    private JButton menuButton;

    /*
  * Layout manager untuk MainForm.
  * Mengatur tata letak komponen-komponen di MainForm.
     */
    private class MainFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
//          ukuran preferensi adalah 5x5
            synchronized (parent.getTreeLock())
            {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock())
            {
//              ukuran minimal adalah 0x0
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
//          menyusun ulang letak komponen-komponen dalam MainForm
            synchronized (parent.getTreeLock())
            {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menubX;
                if (ltr)
                {
                    menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                } else
                {
                    menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
                }
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
    }
}
