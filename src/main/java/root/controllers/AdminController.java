package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import root.database.IProductRepository;
import root.model.Product;
import root.services.IProductService;
import root.session.SessionObject;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

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
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute Product product, @RequestParam MultipartFile obrazek) {
        System.out.println(obrazek);
        try {
            String filePath = "F:\\JAVA\\MAVEN\\beztytulu.jpg";
            obrazek.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        IProductService.AddProductResult result = this.productService.addProduct(product);
        if(result == IProductService.AddProductResult.LENGTH_ADDED) {
            this.sessionObject.setInfo("Lenght increase");
        } else if(result == IProductService.AddProductResult.PRODUCT_ADDED) {
            this.sessionObject.setInfo("New product added");
        }
        return "redirect:/addProduct";
    }

    @RequestMapping(value = "/editProduct/{id}", method = RequestMethod.GET)
    public String editProductPage(@PathVariable int id, Model model) {
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