package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import root.model.Order;
import root.services.IOrderService;
import root.session.SessionObject;
import java.util.List;

import javax.annotation.Resource;

@Controller
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/confirm-order", method = RequestMethod.GET)
    public String confirmOrder() {
        this.orderService.confirmOrder();
        return "redirect:/main";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        List<Order> orders = orderService.getOrdersForCurrentUser();
        model.addAttribute("orders", orders);
        model.addAttribute("user", this.sessionObject.getUser());
        return "orders";
    }
}
