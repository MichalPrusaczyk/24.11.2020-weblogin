package root.dao;

import root.model.Order;
import java.util.List;

public interface IOrderDAO {
    void persistOrder(Order order);
    List<Order> getOrdersByUser(int userId);
}