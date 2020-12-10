package root.services;

import root.model.Product;

import java.util.List;

public interface IProductService {
    AddProductResult addProduct(Product product);
        Product getProductByEAN(String ean);
        Product getProductById(int id);
        void updateProduct(Product product);
        List<Product> getProductsByCategoryWithFilter(String category);

enum AddProductResult {
    LENGTH_ADDED,
    PRODUCT_ADDED
}
}