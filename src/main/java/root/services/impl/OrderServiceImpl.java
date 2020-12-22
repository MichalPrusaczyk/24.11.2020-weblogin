package root.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.dao.IProductDAO;
import root.dao.IOrderDAO;
import root.model.Product;
import root.model.Order;
import root.model.OrderPosition;
import root.services.IOrderService;
import root.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IProductDAO productDAO;

    @Autowired
    IOrderDAO orderDAO;

    @Override
    public void confirmOrder() {
        //Pobieramy koszyk
        List<Product> orderedProducts = this.sessionObject.getBasket();

        for(Product productFromBasket : orderedProducts) {
            Product productFromDB = this.productDAO.getProductById(productFromBasket.getId());
            if(productFromDB.getLength() < productFromBasket.getLength()) {
                return;
            }
        }

        //tworzymy zamowienie
        Order order = new Order();
        //dodajemy usera do zamowienia
        order.setUser(this.sessionObject.getUser());
        //wyliczamy kwote zamowienia
        double bill = 0;
        for(Product product : orderedProducts) {
            bill = bill + product.getPrice() * product.getLength();
        }
        order.setPrice(bill);
        //ustawiamy status zamowienia
        order.setStatus(Order.Status.ORDERED);
        //tworzymy pozycje zamowienia na podstawie koszyka
        for(Product product : orderedProducts) {
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setLength(product.getLength());
            orderPosition.setOrder(order);
            orderPosition.setProduct(product);

            order.getPositions().add(orderPosition);
        }

        this.orderDAO.persistOrder(order);

        for(Product product : orderedProducts) {
            Product productFromDB = this.productDAO.getProductById(product.getId());
            productFromDB.setLength(productFromDB.getLength() - product.getLength());
            this.productDAO.updateProduct(productFromDB);
        }

        this.sessionObject.getBasket().clear();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUser(this.sessionObject.getUser().getId());
    }
}