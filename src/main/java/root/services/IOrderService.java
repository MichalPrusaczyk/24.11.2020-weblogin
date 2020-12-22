package root.services;


import root.model.Order;
import root.model.User;

import java.util.List;

public interface IOrderService {
    void confirmOrder();
    List<Order> getOrdersForCurrentUser();
}