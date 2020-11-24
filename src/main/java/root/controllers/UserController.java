package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;
import root.database.IUserRepository;
import root.model.User;
import root.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public String loginForm(Model model){
        model.addAttribute("userModel", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authentication(@ModelAttribute User user){

        this.sessionObject.setUser(this.userRepository.authenticate(user));

        if(this.sessionObject.getUser() != null){
            return "redirect:/home";
        }else{
            return "redirect:/login";
        }
    }
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(){
        this.sessionObject.setUser(null);
        return "redirect:/login";
    }

    @RequestMapping (value ="/edit", method = RequestMethod.GET)
    public String edit(Model model){
        model.addAttribute("user", this.sessionObject.getUser());
        return "edit";
    }
}
