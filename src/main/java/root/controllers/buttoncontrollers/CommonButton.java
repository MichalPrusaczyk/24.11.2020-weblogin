package root.controllers.buttoncontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import root.database.impl.buttonrepository.IButtonRepository;
import root.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonButton {

    @Autowired
    IButtonRepository buttonRepository;

    @Resource
    SessionObject sessionObject;


    @RequestMapping(value = "/button3", method = RequestMethod.GET)
    public String button3(Model model) {
        if (sessionObject.isLogged()) {
            model.addAttribute("button3Products", this.buttonRepository.getButton3());
            model.addAttribute("user", this.sessionObject.getUser());

            return "main";
        } else {
            return "redirect:/login";
        }
    }
}



