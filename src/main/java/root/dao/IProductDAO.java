package root.dao;

import root.model.Product;

import java.util.List;

    public interface IProductDAO  {
        Product getProductByEAN(String ean);
        void updateProduct(Product product);
        void persistProduct(Product product);
        Product getProductById(int id);
        List<Product> getProductsByCategory(Product.Category category);
        List<Product> getAllProducts();
    }