package fish.service;

import fish.po.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct() throws Exception;

    boolean updateProduct(Product p) throws Exception;

    boolean deleteProduct(String id) throws Exception;

    //返回-1，表示已存在，返回0插入失败，返回1插入成功
    int addProduct(Product p) throws Exception;
}
