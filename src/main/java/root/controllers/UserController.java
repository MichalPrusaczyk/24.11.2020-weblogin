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
import root.model.view.ChangePassData;
import root.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("userModel", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authentication(@ModelAttribute User user) {

        Pattern regexPattern = Pattern.compile(".{3}.*");
        Matcher loginMatcher = regexPattern.matcher(user.getLogin());
        Matcher passMatcher = regexPattern.matcher(user.getPass());

        if(!loginMatcher.matches() || !passMatcher.matches()){
            this.sessionObject.setInfo("invalid value(regexp)");
            return "redirect:/login";
        }

        this.sessionObject.setUser(this.userRepository.authenticate(user));

        if (this.sessionObject.getUser() != null) {
            return "redirect:/home";
        } else {
            this.sessionObject.setInfo("invalid value");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.sessionObject.setUser(null);
        return "redirect:/login";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model) {
        if (this.sessionObject.isLogged()) {
            model.addAttribute("user", this.sessionObject.getUser());
            model.addAttribute("passModel",new ChangePassData());
            return "edit";
        } else {
            return "redirect:/login";
        }
    }
    @RequestMapping(value = "/changeData", method = RequestMethod.POST)
    public String changeData(@ModelAttribute User user){
        user.setLogin(this.sessionObject.getUser().getLogin());
        User updatedUser = this.userRepository.updateUserData(user);
        this.sessionObject.setUser(updatedUser);
        return "redirect:/edit";
    }

    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    public String changePass(@ModelAttribute ChangePassData changePassData){

        if(!changePassData.getNewPass().equals(changePassData.getRepeatedNewPass())){
            //TODO something to do, bad password
        }
        User user = new User();
        user.setPass(changePassData.getPass());
        user.setLogin(this.sessionObject.getUser().getLogin());

        User authenticatedUser = this.userRepository.authenticate(user);

        if(authenticatedUser == null){
            //TODO something to do, bad password
        }

        user.setPass(changePassData.getNewPass());
        User updatedUser = this.userRepository.updateUserPass(user);
        this.sessionObject.setUser(updatedUser);

        return "redirect:/edit";
    }
}
