package root.database.impl;

import org.springframework.stereotype.Component;
import root.database.IProductRepository;
import root.model.Product;

import java.util.ArrayList;
import java.util.List;
@Component
public class ListProductRepositoryImpl implements IProductRepository {

    private final List<Product> products = new ArrayList<>();

//    {
//        this.product.add(new Product("Aluminium", "round", 100, "fi10",200.00, Product.Category.BUTTON1,"123123123"));
//        this.product.add(new Product("Steel", "round", 100, "fi10",100.00, Product.Category.BUTTON2,"456456456"));
//    }


    @Override
    public List<Product> getAllProducts() {
        return this.products;
    }

    @Override
    public List<Product> getProductsByCategory(Product.Category category) {
        //TODO Do zrobienia
        return null;
    }

    @Override
    public Product getProductByEAN(String ean) {
        for(Product product : this.products) {
            if(product.getEan().equals(ean)) {
                return product;
            }
        }

        return null;
    }

    @Override
    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public void updateProduct(Product product) {
        //TODO do zrobienia
    }
}
