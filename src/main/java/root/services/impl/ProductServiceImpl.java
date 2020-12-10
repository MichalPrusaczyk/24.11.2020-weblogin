package root.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.dao.IProductDAO;
import root.model.Product;
import root.services.IProductService;
import root.session.SessionObject;
import root.utils.FilterUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductDAO productDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public AddProductResult addProduct(Product product) {
        Product productFromDB = this.productDAO.getProductByEAN(product.getEan());
        if(productFromDB == null) {
            this.productDAO.persistProduct(product);
            return AddProductResult.PRODUCT_ADDED;
        } else {
            productFromDB.setLength(productFromDB.getLength() + product.getLength());
            this.productDAO.updateProduct(productFromDB);
            return AddProductResult.LENGTH_ADDED;
        }
    }

    @Override
    public Product getProductByEAN(String ean) {
        return this.productDAO.getProductByEAN(ean);
    }

    @Override
    public Product getProductById(int id) {
        return this.productDAO.getProductById(id);
    }

    @Override
    public void updateProduct(Product product) {
        this.productDAO.updateProduct(product);
    }

    @Override
    public List<Product> getProductsByCategoryWithFilter(String category) {
        switch (category) {
            case "button1":
                return FilterUtils.filterProducts(this.productDAO.getProductsByCategory(Product.Category.BUTTON1),
                        this.sessionObject.getFilter());
            case "button2":
                return FilterUtils.filterProducts(this.productDAO.getProductsByCategory(Product.Category.BUTTON2),
                        this.sessionObject.getFilter());

            default:
                return FilterUtils.filterProducts(this.productDAO.getAllProducts(),
                        this.sessionObject.getFilter());
        }
    }
}