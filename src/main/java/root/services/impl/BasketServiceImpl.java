package root.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.dao.IProductDAO;
import root.model.Product;
import root.services.IBasketService;
import root.session.SessionObject;

import javax.annotation.Resource;

@Service
public class BasketServiceImpl implements IBasketService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IProductDAO productDAO;

    @Override
    public void addToBasket(int productId) {
        for(Product product : this.sessionObject.getBasket()) {
            if(product.getId() == productId) {
                product.setLength(product.getLength()+1);
                return;
            }
        }

        Product product = this.productDAO.getProductById(productId);
        product.setLength(1);
        this.sessionObject.getBasket().add(product);
    }

    @Override
    public double calculateBill() {
        double bill = 0;
        for(Product product : this.sessionObject.getBasket()) {
            bill = bill + product.getPrice() * product.getLength();
        }
        return bill;
    }
}