package fish.dao;

import fish.po.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao {
    List<Product> getAllProduct() throws Exception;
    boolean updateProduct(Product p) throws Exception;
    boolean deleteProduct(String id) throws Exception;
    boolean checkExistProduct(String id) throws Exception;
    boolean addProduct(Product p) throws Exception;
}
