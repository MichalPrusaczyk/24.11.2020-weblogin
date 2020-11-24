package root.database.impl;

import org.springframework.stereotype.Component;
import root.database.IProductRepository;
import root.model.Product;

import java.util.ArrayList;
import java.util.List;
@Component
public class ListProductRepositoryImpl implements IProductRepository {

    private final List<Product> product = new ArrayList<>();

    {
        this.product.add(new Product("Aluminium", "round", 100, "fi10", Product.Category.BUTTON1));
        this.product.add(new Product("Steel", "round", 100, "fi10", Product.Category.BUTTON2));
    }


    @Override
    public List<Product> getAllProducts() {
        return this.product;
    }

    @Override
    public List<Product> getButton1() {
        List<Product> button1Products = new ArrayList<>();

        for (Product product : this.product) {
            if(product.getCategory() == Product.Category.BUTTON1){
            button1Products.add(product);
        }
    }

    return button1Products;
}

    @Override
    public List<Product> getButton2() {
        List<Product> button2Products = new ArrayList<>();

        for (Product product : this.product) {
            if(product.getCategory() == Product.Category.BUTTON2){
                button2Products.add(product);
            }
        }

        return button2Products;
    }

    @Override
    public List<Product> getButton4() {
        List<Product> button2Products = new ArrayList<>();

        for (Product product : this.product) {
            if(product.getCategory() == Product.Category.BUTTON4){
                button2Products.add(product);
            }
        }

        return button2Products;
    }

    @Override
    public List<Product> getProductsByFilter(String filter) {
        List<Product> filteredProducts = new ArrayList<>();

        for(Product product : this.product){
            if(product.getShape().toUpperCase().contains(filter.toUpperCase())){
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }
}
