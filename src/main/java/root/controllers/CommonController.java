package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import root.database.IProductRepository;
import root.model.Product;
import root.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    IProductRepository productRepository;


    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("products", this.productRepository.getAllProducts());
        model.addAttribute("user",this.sessionObject.getUser());
        if(sessionObject.isLogged()){
            return "main";
        }else{
            return "redirect:/login";
        }

    }

    @RequestMapping(value ="/button1", method = RequestMethod.GET)
    public String button1(Model model) {
        if (sessionObject.isLogged()) {
            model.addAttribute("products", this.productRepository.getButton1());
            model.addAttribute("user", this.sessionObject.getUser());

            return "main";
        }else {
            return "redirect:/login";
        }
    }


    @RequestMapping(value ="/button2", method = RequestMethod.GET)
    public String button2(Model model) {
        if (sessionObject.isLogged()) {
            model.addAttribute("products", this.productRepository.getButton2());
            model.addAttribute("user", this.sessionObject.getUser());

            return "main";
        } else {
            return "redirect:/login";
        }
    }
    @RequestMapping(value ="/button4", method = RequestMethod.GET)
    public String button4(Model model) {
        if (sessionObject.isLogged()) {
            model.addAttribute("products", this.productRepository.getButton4());
            model.addAttribute("user", this.sessionObject.getUser());

            return "main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam String filter, Model model){

        if (sessionObject.isLogged()) {
        model.addAttribute("products",this.productRepository.getProductsByFilter(filter));
        model.addAttribute("user",this.sessionObject.getUser());

        return "main";
    }else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String contact(Model model){

        if (sessionObject.isLogged()) {
        model.addAttribute("user",this.sessionObject.getUser());

        return "home";
    }else {
            return "redirect:/login";
        }
    }
}

