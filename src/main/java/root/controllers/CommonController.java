package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import root.database.IProductRepository;
import root.model.Product;
import root.services.IProductService;
import root.session.SessionObject;
import root.utils.FilterUtils;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    IProductService productService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String commonRedirect() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model, @RequestParam(defaultValue = "none") String category) {
        if (sessionObject.isLogged()) {
            List<Product> mainStoreProducts = this.productService.getProductsByCategoryWithFilter(category);
            for (Product productFormMainStore : mainStoreProducts) {
                for (Product productFormBasket : this.sessionObject.getBasket()) {
                    if (productFormMainStore.getId() == productFormBasket.getId()) {
                        productFormMainStore.setLength(productFormMainStore.getLength() - productFormBasket.getLength());
                    }
                }
            }
            model.addAttribute("products", mainStoreProducts);
            model.addAttribute("user", this.sessionObject.getUser());
            model.addAttribute("filter", this.sessionObject.getFilter());
            return "main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam String filter) {
        if (sessionObject.isLogged()) {
            this.sessionObject.setFilter(filter);
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        if (sessionObject.isLogged()) {
            model.addAttribute("user", this.sessionObject.getUser());
            return "contact";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        if (sessionObject.isLogged()) {
            model.addAttribute("user", this.sessionObject.getUser());
            return "home";
        } else {
            return "redirect:/login";
        }
    }
}