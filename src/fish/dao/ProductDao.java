package fish.dao;

import fish.po.Product;
import fish.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    @Override
    public List<Product> getAllProduct() throws Exception {
        Connection con = DBUtil.createConnection();
        Statement sta = con.createStatement();
        ResultSet rst = sta.executeQuery("select * from tbl_product");
        List<Product> productList = new ArrayList<Product>();

        while (rst.next()) {
            Product p = new Product();
            p.setProductID(rst.getString(1));
            p.setProductName(rst.getString(2));
            p.setSafeStock(rst.getInt(3));
            p.setLastPurchaseDate(rst.getDate(4));
            p.setLastDeliveryDateDate(rst.getDate(5));
            p.setQuantity(rst.getInt(6));
            p.setSuggestBuyPrice(rst.getString(7));
            p.setSuggestSalePrice(rst.getString(8));
            productList.add(p);
        }
        DBUtil.disconnect(con, sta, rst);
        return productList;
    }

    @Override
    public boolean updateProduct(Product p) throws Exception {
        boolean flag = false;

        Connection con = DBUtil.createConnection();
        String sql = "update tbl_product set ProductName = ?, SafeStock = ?, SuggestBuyPrice = ?, SuggestSalePrice = ?\n" +
                "where ProductID = ?";
        PreparedStatement psta = con.prepareStatement(sql);
        psta.setString(1, p.getProductName());
        psta.setInt(2, p.getSafeStock());
        psta.setFloat(3, p.getSuggestBuyPrice());
        psta.setFloat(4, p.getSuggestSalePrice());
        psta.setString(5, p.getProductID());
        int result = psta.executeUpdate();

        if (result > 0) flag = true;

        DBUtil.disconnect(con, psta);

        return flag;
    }

    @Override
    public boolean deleteProduct(String id) throws Exception {
        Connection con = DBUtil.createConnection();
        boolean flag = false;
        String sql = "delete \n" +
                "from tbl_product\n" +
                "where ProductID = ?";
        PreparedStatement psta = con.prepareStatement(sql);
        psta.setString(1, id);
        int result = psta.executeUpdate();
        if (result > 0) flag = true;
        DBUtil.disconnect(con, psta);

        return flag;
    }

    @Override
    public boolean checkExistProduct(String id) throws Exception {
        Connection con = null;
        PreparedStatement psta = null;
        ResultSet rs = null;
        boolean flag = false;
        String sql = "select COUNT(*) from tbl_product where ProductID=?";
        con = DBUtil.createConnection();
        psta = con.prepareStatement(sql);
        psta.setString(1, id);
        rs = psta.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            if (count > 0) flag = true;
        }
        DBUtil.disconnect(con, psta, rs);
        return flag;
    }

    @Override
    public boolean addProduct(Product p) throws Exception {
        Connection con = DBUtil.createConnection();
        boolean flag = false;
        String sql = "insert into tbl_product(ProductID, ProductName, SafeStock, SuggestBuyPrice, SuggestSalePrice, LastPurchaseDate)\n" +
                "values(?, ?, ?, ?, ?, ?)";
        PreparedStatement psta = con.prepareStatement(sql);
        psta.setString(1, p.getProductID());
        psta.setString(2, p.getProductName());
        psta.setInt(3, p.getSafeStock());

        if (p.getSuggestBuyPrice() == null) psta.setNull(4, Types.FLOAT);
        else psta.setFloat(4, p.getSuggestBuyPrice());
        if (p.getSuggestSalePrice() == null) psta.setNull(5, Types.FLOAT);
        else psta.setFloat(5, p.getSuggestSalePrice());

        psta.setDate(6, new java.sql.Date(new java.util.Date().getTime()));

        int result = psta.executeUpdate();
        if (result > 0) flag = true;
        DBUtil.disconnect(con, psta);
        return flag;
    }
}
