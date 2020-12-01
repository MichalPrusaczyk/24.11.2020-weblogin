package root.database;

import root.model.Product;

import java.util.List;

public interface IProductRepository {
        List<Product> getAllProducts();

        List<Product> getProductsByCategory(Product.Category category);

        Product getProductByEAN(String ean);

        void addProduct(Product product);

        void updateProduct(Product product);
    }
