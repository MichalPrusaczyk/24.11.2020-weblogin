package root.database;

import root.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getAllProducts();
    List<Product> getButton1();
    List<Product> getButton2();
    List<Product> getProductsByFilter(String filter);

    List<Product> getButton4();
}
