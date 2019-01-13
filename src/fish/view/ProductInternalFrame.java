package fish.view;

import fish.po.Product;
import fish.service.IProductService;
import fish.service.ProductService;
import fish.util.Validator;
import fish.util.ViewUtil;
import javafx.concurrent.Service;
import jdk.jshell.execution.Util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductInternalFrame extends JInternalFrame {
    private final JScrollPane scrollPane;
    private JTable productTable;
    private JTextField productIDTextField;
    private JTextField safeStockTextField;
    private JTextField productNameTextField;
    private JTextField suggestBuyPriceTextField;
    private JTextField suggestSalePriceTextField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductInternalFrame frame = new ProductInternalFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ProductInternalFrame() {


        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("商品资料管理");
        //设定居中显示
        setBounds(100, 100, 800, 400);
        ViewUtil.setScreenCenter(this);

        getContentPane().setLayout(null);


        productTable = new JTable();
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                getTableDataToField();
            }
        });
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "\u7F16\u53F7", "\u4EA7\u54C1\u540D\u79F0", "\u5B89\u5168\u5B58\u91CF", "\u5EFA\u8BAE\u8D2D\u4E70\u4EF7\u683C", "\u5EFA\u8BAE\u9500\u552E\u4EF7\u683C", "\u5F53\u524D\u6570\u91CF", "\u6700\u540E\u8FDB\u8D27\u65E5\u671F", "\u6700\u540E\u9001\u8D27\u65E5\u671F"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    String.class, String.class, Integer.class, Float.class, Float.class, Integer.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
//                return super.isCellEditable(row, column);
            }

        });

        displayData(getData());

        scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(0, 0, 784, 188);
        getContentPane().add(scrollPane);

        JPanel panel = new JPanel();
        panel.setBounds(0, 198, 784, 94);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel productIDLabel = new JLabel("\u624B\u5DE5\u7F16\u53F7");
        productIDLabel.setForeground(Color.RED);
        productIDLabel.setBounds(10, 27, 54, 15);
        panel.add(productIDLabel);

        JLabel productNameLabel = new JLabel("\u4EA7\u54C1\u540D\u79F0");
        productNameLabel.setForeground(Color.RED);
        productNameLabel.setBounds(269, 27, 54, 15);
        panel.add(productNameLabel);

        JLabel safeStockLabel = new JLabel("\u5B89\u5168\u5B58\u91CF");
        safeStockLabel.setForeground(Color.RED);
        safeStockLabel.setBounds(10, 69, 54, 15);
        panel.add(safeStockLabel);

        JLabel suggestBuyPriceLabel = new JLabel("\u5EFA\u8BAE\u8D2D\u4E70\u4EF7\u683C");
        suggestBuyPriceLabel.setBounds(247, 69, 93, 15);
        panel.add(suggestBuyPriceLabel);

        JLabel suggestSalePriceLabel = new JLabel("\u5EFA\u8BAE\u9500\u552E\u4EF7\u683C");
        suggestSalePriceLabel.setBounds(512, 69, 112, 15);
        panel.add(suggestSalePriceLabel);

        productIDTextField = new JTextField();
        productIDTextField.setBounds(78, 24, 142, 21);
        panel.add(productIDTextField);
        productIDTextField.setColumns(10);

        safeStockTextField = new JTextField();
        safeStockTextField.setBounds(78, 66, 142, 21);
        panel.add(safeStockTextField);
        safeStockTextField.setColumns(10);

        productNameTextField = new JTextField();
        productNameTextField.setBounds(333, 24, 395, 21);
        panel.add(productNameTextField);
        productNameTextField.setColumns(10);

        suggestBuyPriceTextField = new JTextField();
        suggestBuyPriceTextField.setBounds(333, 66, 132, 21);
        panel.add(suggestBuyPriceTextField);
        suggestBuyPriceTextField.setColumns(10);

        suggestSalePriceTextField = new JTextField();
        suggestSalePriceTextField.setBounds(597, 66, 131, 21);
        panel.add(suggestSalePriceTextField);
        suggestSalePriceTextField.setColumns(10);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 305, 784, 59);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("\u6CE8\u610F\uFF1A\u7EA2\u989C\u8272\u4E3A\u5FC5\u9009\u9879");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setBounds(10, 21, 162, 15);
        panel_1.add(lblNewLabel);

        JButton addButton = new JButton("\u65B0\u589E");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doAdd();
            }
        });
        addButton.setBounds(235, 17, 93, 23);
        panel_1.add(addButton);

        JButton deleteButton = new JButton("\u5220\u9664");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doDelete();
            }
        });
        deleteButton.setBounds(628, 17, 93, 23);
        panel_1.add(deleteButton);

        JButton queryButton = new JButton("\u67E5\u8BE2");
        queryButton.setBounds(499, 17, 93, 23);
        panel_1.add(queryButton);

        JButton updateButton = new JButton("\u66F4\u65B0");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                doUpdate();
            }
        });
        updateButton.setBounds(367, 17, 93, 23);
        panel_1.add(updateButton);

    }

    private Product getSelectProduct() {
        String id = productIDTextField.getText();
        String name = productNameTextField.getText();
        String safeStock = safeStockTextField.getText();
        String suggestBuyPrice = suggestBuyPriceTextField.getText().trim();
        String suggestSalePrice = suggestSalePriceTextField.getText().trim();
        Product p = new Product();
        p.setProductID(id);
        p.setProductName(name);
        p.setSafeStock(safeStock);
        p.setSuggestBuyPrice(suggestBuyPrice);
        p.setSuggestSalePrice(suggestSalePrice);
        return p;
    }

    private void doAdd() {
        Product p = getSelectProduct();

        if (Validator.isNotNull(p.getProductID()) && Validator.isNotNull(p.getProductName()) && p.getSafeStock() != null) {
            IProductService service = new ProductService();
            int result = 0;
            try {
                result = service.addProduct(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (result == -1) JOptionPane.showMessageDialog(null, "产品已存在");
            else if (result == 0) JOptionPane.showMessageDialog(null, "添加失败");
            else if (result == 1) {
                JOptionPane.showMessageDialog(null, "添加成功");
                displayData(getData());
            }
        } else {
            JOptionPane.showMessageDialog(null, "必填项不能为空");
        }
    }

    private void doDelete() {
        String id = productIDTextField.getText();
        IProductService service = new ProductService();
        boolean result = false;
        try {
            result = service.deleteProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result) {
            JOptionPane.showMessageDialog(null, "删除成功");
            displayData(getData());
        } else {
            JOptionPane.showMessageDialog(null, "删除失败");
        }
    }

    public void displayData(List<Product> productList) {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();

        if (model.getRowCount() > 0) model.setRowCount(0);

        for (Product p : productList) {
            Vector<Object> v = new Vector<Object>();
            v.add(p.getProductID());
            v.add(p.getProductName());
            v.add(p.getSafeStock());
            v.add(p.getSuggestBuyPrice());
            v.add(p.getSuggestSalePrice());
            v.add(p.getQuantity());
            v.add(p.getLastPurchaseDate());
            v.add(p.getLastDeliveryDateDate());

            model.addRow(v);
        }
    }

    public List<Product> getData() {
        ProductService service = new ProductService();
        List<Product> productList = new ArrayList<Product>();
        try {
            productList = service.getAllProduct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void getTableDataToField() {
        int selectRow = productTable.getSelectedRow();
        productIDTextField.setText((String) productTable.getValueAt(selectRow, 0));
        productNameTextField.setText((String) productTable.getValueAt(selectRow, 1));
        safeStockTextField.setText(productTable.getValueAt(selectRow, 2).toString());

        Object a = productTable.getValueAt(selectRow, 3);
        suggestBuyPriceTextField.setText(a == null ? null : a.toString());
        a = productTable.getValueAt(selectRow, 4);
        suggestSalePriceTextField.setText(a == null ? null : a.toString());
    }

    public void doUpdate() {
        Product p = getSelectProduct();
        if (Validator.isNotNull(p.getProductID()) && Validator.isNotNull(p.getProductName()) && p.getSafeStock() != null) {
            boolean result = false;
            IProductService service = new ProductService();
            try {
                result = service.updateProduct(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (result) {
                JOptionPane.showMessageDialog(null, "更新成功");
                displayData(getData());
            } else {
                JOptionPane.showMessageDialog(null, "更新失败");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请确保所有必填项都已填写完毕，并且安全存量为数值");
        }
    }
}
