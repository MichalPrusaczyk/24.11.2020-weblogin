package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import root.services.IProductService;
import root.session.SessionObject;


import javax.annotation.Resource;


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
        if(sessionObject.isLogged()) {
            model.addAttribute("products", this.productService.getProductsByCategoryWithFilter(category));
            model.addAttribute("user", this.sessionObject.getUser());
            model.addAttribute("filter", this.sessionObject.getFilter());
            return "main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam String filter) {
        if(sessionObject.isLogged()) {
            this.sessionObject.setFilter(filter);
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        if(sessionObject.isLogged()) {
            model.addAttribute("user", this.sessionObject.getUser());
            return "contact";
        } else {
            return "redirect:/login";
        }
    }
}