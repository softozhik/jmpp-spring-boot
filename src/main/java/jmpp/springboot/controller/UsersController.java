package jmpp.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import jmpp.springboot.model.User;
import jmpp.springboot.service.UserService;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/main")
    public String allUsers(ModelMap model, Authentication autUser) {
        User loginUser = userService.findUserByUsername(autUser.getName());
        model.addAttribute("loginUser", loginUser);
        return "main";
    }
}
