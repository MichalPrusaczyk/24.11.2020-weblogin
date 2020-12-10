package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import root.database.IProductRepository;
import root.services.IProductService;
import root.session.SessionObject;
import root.database.IProductRepository;
import root.model.Product;
import root.session.SessionObject;

import javax.annotation.Resource;
import java.awt.print.Book;

@Controller
public class AdminController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IProductService productService;

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProductForm(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("user", this.sessionObject.getUser());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("book", new Product());
        return "addProduct";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute Product product) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        IProductService.AddProductResult result = this.productService.addProduct(product);
        if(result == IProductService.AddProductResult.LENGTH_ADDED) {
            this.sessionObject.setInfo("Product added to store");
        } else if(result == IProductService.AddProductResult.PRODUCT_ADDED) {
            this.sessionObject.setInfo("New product");
        }
        return "redirect:/addProduct";
    }

    @RequestMapping(value = "/editProduct/{id}", method = RequestMethod.GET)
    public String editBookPage(@PathVariable int id, Model model) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("user", this.sessionObject.getUser());
        return "editProduct";
    }

    @RequestMapping(value = "/editProduct/{id}", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute Product product, @PathVariable int id) {
        product.setId(id);
        this.productService.updateProduct(product);

        return "redirect:/main";
    }
}