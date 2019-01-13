package fish.service;

import fish.dao.IProductDao;
import fish.dao.ProductDao;
import fish.po.Product;

import java.util.List;

public class ProductService implements IProductService {
    @Override
    public List<Product> getAllProduct() throws Exception {
        IProductDao dao = new ProductDao();
        return dao.getAllProduct();
    }

    @Override
    public boolean updateProduct(Product p) throws Exception {
        IProductDao dao = new ProductDao();
        return dao.updateProduct(p);
    }

    @Override
    public boolean deleteProduct(String id) throws Exception {
        IProductDao dao = new ProductDao();
        return dao.deleteProduct(id);
    }

    @Override
    public int addProduct(Product p) throws Exception {
        int result = 0;
        IProductDao dao = new ProductDao();
        if (dao.checkExistProduct(p.getProductID())) result = -1;
        if (result != -1) {
            if (dao.addProduct(p)) result = 1;
            else result = 0;
        }
        return result;
    }
}
