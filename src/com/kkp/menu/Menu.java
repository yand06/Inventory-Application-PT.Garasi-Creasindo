package com.kkp.menu;

import raven.menu.mode.LightDarkMode;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import raven.menu.mode.ToolBarAccentColor;

public class Menu extends JPanel {

    private final String menuItems[][] =
    {
//        {"~Dashboard~"},
        {
            "Dashboard"
        },
        //        {"~APP~"},
        {
            "Bahan Baku Digital", "Stok Masuk", "Stok Keluar"
        },
        {
            "Bahan Baku Produksi", "Stok Masuk", "Stok Keluar"
        },
        {
            "Report", "Bahan Baku Digital", "Bahan Baku Produksi"
        },
        {
            "~~"
        },
        {
            "~Account~"
        },
        {
            "Logout"
        }
    };

    public boolean isMenuFull() {
        return menuFull;
    }

    public void setMenuFull(boolean menuFull) {
        this.menuFull = menuFull;
        if (menuFull)
        {
            header.setText(headerName);
            header.setHorizontalAlignment(getComponentOrientation().isLeftToRight() ? JLabel.LEFT : JLabel.RIGHT);
        } else
        {
            header.setText("");
            header.setHorizontalAlignment(JLabel.CENTER);
        }
        for (Component com : panelMenu.getComponents())
        {
            if (com instanceof MenuItem)
            {
                ((MenuItem) com).setFull(menuFull);
            }
        }
        lightDarkMode.setMenuFull(menuFull);
        toolBarAccentColor.setMenuFull(menuFull);
    }

    private final List<MenuEvent> events = new ArrayList<>();
    private boolean menuFull = true;
    private final String headerName = "PT. Garasi Creasindo";

    protected final boolean hideMenuTitleOnMinimum = true;
    protected final int menuTitleLeftInset = 5;
    protected final int menuTitleVgap = 5;
    protected final int menuMaxWidth = 250;
    protected final int menuMinWidth = 60;
    protected final int headerFullHgap = 5;

    public Menu() {
        init();
    }

    private void init() {
        setLayout(new MenuLayout());
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:20,2,2,2;"
                + "background:$Menu.background;"
                + "arc:10");
        header = new JLabel(headerName);
        header.setIcon(new ImageIcon(getClass().getResource("/raven/icon/png/logoHeader.png")));
        header.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$Menu.header.font;"
                + "foreground:$Menu.foreground");

        scroll = new JScrollPane();
        panelMenu = new JPanel(new MenuItemLayout(this));
        panelMenu.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5;"
                + "background:$Menu.background");
        scroll.setViewportView(panelMenu);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:null");
        JScrollBar vscroll = scroll.getVerticalScrollBar();
        vscroll.setUnitIncrement(10);
        vscroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "width:$Menu.scroll.width;"
                + "trackInsets:$Menu.scroll.trackInsets;"
                + "thumbInsets:$Menu.scroll.thumbInsets;"
                + "background:$Menu.ScrollBar.background;"
                + "thumb:$Menu.ScrollBar.thumb");

        createMenu();
        lightDarkMode = new LightDarkMode();
        toolBarAccentColor = new ToolBarAccentColor(this);
        toolBarAccentColor.setVisible(FlatUIUtils.getUIBoolean("AccentControl.show", false));
        add(header);
        add(scroll);
        add(lightDarkMode);
        add(toolBarAccentColor);
    }

    private void createMenu() {
        int index = 0;
        for (int i = 0; i < menuItems.length; i++)
        {
            String menuName = menuItems[i][0];
            if (menuName.startsWith("~") && menuName.endsWith("~"))
            {
//              jika item menu diawali & diakhiri dengan "~" buat jLabel sebagai judul menu
                panelMenu.add(createTitle(menuName));
            } else
            {
//              jika bukan judul, buat MenuItem untuk item menu tersebut
                MenuItem menuItem = new MenuItem(this, menuItems[i], index++, events);
                panelMenu.add(menuItem);
            }
        }
    }

//  metode untuk membuat jLabel sebagai judul menu
    private JLabel createTitle(String title) {
//      Mengambil string dari parameter title, memotong karakter pertama dan terakhir,
//      lalu menyimpannya ke dalam variabel menuName.
//      Misalnya, jika title adalah "~MAIN (UTAMA)~", maka menuName akan menjadi "MAIN (UTAMA)"
        String menuName = title.substring(1, title.length() - 1);
//      buat jLabel dengan teks menuName
        JLabel lbTitle = new JLabel(menuName);
//      atur font dan warna teks jLabel sesuai pengaturan aplikasi
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$Menu.label.font;"
                + "foreground:$Menu.title.foreground");
//      kembalikan jLabel yang telah dibuat
        return lbTitle;
    }

//  metode untuk mengatur menu yang dipilih
    public void setSelectedMenu(int index, int subIndex) {
        runEvent(index, subIndex);
    }

//  metode untuk mengatur menu yang dipilih (internal)
    protected void setSelected(int index, int subIndex) {
//      mendapatkan jumlah komponen dalam panelMenu
        int size = panelMenu.getComponentCount();
//      melakukan iterasi untuk setiap komponen dalam penelMenu
        for (int i = 0; i < size; i++)
        {
            Component com = panelMenu.getComponent(i);
//          jika komponen merupakan instance dari MenuItem
            if (com instanceof MenuItem)
            {
                MenuItem item = (MenuItem) com;
//              jika index menu item sama dengan index yang diberikan
                if (item.getMenuIndex() == index)
                {
//                  set indekx submenu yang dipilih pada item tersebut
                    item.setSelectedIndex(subIndex);
                } else
                {
//                  set indeks submenu menjadi -1 (tidak ada yang dipilih)
                    item.setSelectedIndex(-1);
                }
            }
        }
    }

//  metode untuk menjalankan event saat menu dipilih
    protected void runEvent(int index, int subIndex) {
//      buat objek MenuAction dari class MenuAction
        MenuAction menuAction = new MenuAction();
//      iterasi setiap MenuEvent dalam event
        for (MenuEvent event : events)
        {
//          jalankan event dengan parameter index, subIndex, dan menuAction
            event.menuSelected(index, subIndex, menuAction);
        }
//      jika tidak dibatalkan
        if (!menuAction.isCancel())
        {
//          set menu yang dipilih
            setSelected(index, subIndex);
        }
    }

//  metode untuk menambahkan MenuEvent
    public void addMenuEvent(MenuEvent event) {
//      tambahkan MenuEvent ke dalam event
        events.add(event);
    }

//  metode untuk menyembunyikan MenuItem
    public void hideMenuItem() {
//      iterasi setiap komponen dalam panelMenu
        for (Component com : panelMenu.getComponents())
        {
//          jika komponen adalah MenuItem
            if (com instanceof MenuItem)
            {
//               panggil hideMenuItem pada komponen tersebut
                ((MenuItem) com).hideMenuItem();
            }
        }
//      memperbarui tampilan
        revalidate();
    }

    public boolean isHideMenuTitleOnMinimum() {
//      mengembalikan nilai boolean dari variabel hideMenuTitleOnMinimum
        return hideMenuTitleOnMinimum;
    }

    public int getMenuTitleLeftInset() {
//      mengembalikan nilai integer dari variabel menuTitleLeftInset
        return menuTitleLeftInset;
    }

    public int getMenuTitleVgap() {
//      mengembalikan nilai integer dari variabel menuTitleVgap
        return menuTitleVgap;
    }

    public int getMenuMaxWidth() {
//      mengembalikan nilai integer dari variabel menuMaxWidth
        return menuMaxWidth;
    }

    public int getMenuMinWidth() {
//      mengembalikan nilai integer dari variabel menuMinWidth
        return menuMinWidth;
    }

//  deklarasi setiap variabel
    private JLabel header;
    private JScrollPane scroll;
    private JPanel panelMenu;
    private LightDarkMode lightDarkMode;
    private ToolBarAccentColor toolBarAccentColor;

//  deklarasi kelas MenuLayout yang mengimplementasikan LayoutManage
    private class MenuLayout implements LayoutManager {
//      metode addLayoutComponent tidak diimplementasikan

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }
//      metode removeLayoutComponent tidak diimplementasikan

        @Override
        public void removeLayoutComponent(Component comp) {
        }
//      metode preferredLayoutSize mengembalikan dimensi 5x5

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock())
            {
                return new Dimension(5, 5);
            }
        }
//      metode minimumLayoutSize mengembalikan dimensi 0x0

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock())
            {
                return new Dimension(0, 0);
            }
        }
//      metode layoutContainer untuk mengatur tata letak komponen-komponen

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock())
            {
//              mendapatkan insets dari parent
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int gap = UIScale.scale(5);
                int sheaderFullHgap = UIScale.scale(headerFullHgap);
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int iconWidth = width;
                int iconHeight = header.getPreferredSize().height;
                int hgap = menuFull ? sheaderFullHgap : 0;
                int accentColorHeight = 0;
                if (toolBarAccentColor.isVisible())
                {
                    accentColorHeight = toolBarAccentColor.getPreferredSize().height + gap;
                }

//              mengatur bounds untuk header
                header.setBounds(x + hgap, y, iconWidth - (hgap * 2), iconHeight);
                int ldgap = UIScale.scale(10);
                int ldWidth = width - ldgap * 2;
                int ldHeight = lightDarkMode.getPreferredSize().height;
                int ldx = x + ldgap;
                int ldy = y + height - ldHeight - ldgap - accentColorHeight;

                int menux = x;
                int menuy = y + iconHeight + gap;
                int menuWidth = width;
                int menuHeight = height - (iconHeight + gap) - (ldHeight + ldgap * 2) - (accentColorHeight);
//              mengatur bounds untuk scroll
                scroll.setBounds(menux, menuy, menuWidth, menuHeight);
//              mengaturr bounds untuk ligthDarkMode
                lightDarkMode.setBounds(ldx, ldy, ldWidth, ldHeight);

                if (toolBarAccentColor.isVisible())
                {
                    int tbheight = toolBarAccentColor.getPreferredSize().height;
                    int tbwidth = Math.min(toolBarAccentColor.getPreferredSize().width, ldWidth);
                    int tby = y + height - tbheight - ldgap;
                    int tbx = ldx + ((ldWidth - tbwidth) / 2);
//                  mengatur bounds untuk toolBarAccentColor
                    toolBarAccentColor.setBounds(tbx, tby, tbwidth, tbheight);
                }
            }
        }
    }
}
