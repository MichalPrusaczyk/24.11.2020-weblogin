package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import root.database.IProductRepository;
import root.model.Product;
import root.services.IBasketService;
import root.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    IBasketService basketService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addToBasket/{id}", method = RequestMethod.GET)
    public String addProductToBasket(@PathVariable int id) {
        this.basketService.addToBasket(id);
        return "redirect:/main";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String showBasket(Model model) {
        model.addAttribute("products", this.sessionObject.getBasket());
        model.addAttribute("user", this.sessionObject.getUser());
        model.addAttribute("bill", this.basketService.calculateBill());
        return "basket";
    }

    @RequestMapping(value = "/remove-product-from-basket/{id}", method = RequestMethod.GET)
    public String removeProductFormBasket(@PathVariable int id) {
        this.basketService.removeProductFromBasket(id);
        return "redirect:/basket";
    }
}