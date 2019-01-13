package fish.view;

import fish.util.JInternalFrameFactory;
import fish.util.ViewUtil;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JButton productBtn;
    private MyDeskTopPanel desktopPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(0, 0, 450, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("\u57FA\u672C\u8D44\u6599");
        menu.setActionCommand("\u57FA\u672C\u8D44\u6599(B)");
        menu.setMnemonic('B');
        menuBar.add(menu);

        JMenuItem productMenuItem = new JMenuItem("\u5546\u54C1\u7BA1\u7406");
        productMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productActionPerformed();
            }
        });
        productMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/image/productmnu.gif")));
        productMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        menu.add(productMenuItem);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JToolBar toolBar = new JToolBar();
        //设置工具栏的宽度和屏幕宽度一样
        toolBar.setBounds(0, 0, ViewUtil.getFullScreen().width, 57);
        contentPane.add(toolBar);

        productBtn = new JButton("");
        productBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productActionPerformed();
            }
        });
        productBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/image/product.gif")));
        toolBar.add(productBtn);

        desktopPane = new MyDeskTopPanel();
        desktopPane.setBounds(0, 56, ViewUtil.getFullScreen().width, ViewUtil.getFullScreen().height - 60);
        desktopPane.setImgURL(MainFrame.class.getResource("/image/elms.jpg"));
        contentPane.add(desktopPane);

        MyDeskTopPanel myDeskTopPanel = new MyDeskTopPanel();
        myDeskTopPanel.setBounds(94, 183, 1, 1);
        desktopPane.add(myDeskTopPanel);
        //让主界面占满整个屏幕
        ViewUtil.setFullScreen(this);
    }

    public void productActionPerformed() {
        ProductInternalFrame pf = JInternalFrameFactory.createPFrame();
        pf.setVisible(true);
        if (!pf.isShowing()) {
            desktopPane.add(pf);
        }
    }
}
