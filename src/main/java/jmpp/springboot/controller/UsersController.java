package jmpp.springboot.controller;

import jmpp.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import jmpp.springboot.model.User;
import jmpp.springboot.service.UserService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/main")
//    @Secured("ROLE_ADMIN")
    public String allUsers(ModelMap model, Authentication autUser) {
        User loginUser = userService.findUserByUsername(autUser.getName());
        List<User> listUsers = userService.listAll();
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("users", listUsers);
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.listAll());
        return "main";
    }
/*

    @PostMapping("/main/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id, @RequestParam(value = "allRoles") String[] roles) { //
//        System.out.println("новые роли: " + roles);
        user.setRoles(userService.updateRoles(roles));
//        System.out.println("обновляем пользователя: " + user);
        userService.update(user);
        return "redirect:/main";
    }

    @PostMapping("/main")
    public String create(@ModelAttribute("user") User user) { //, @RequestParam(value = "allRoles") String[] roles
//        user.setRoles(userService.updateRoles(roles));
//        System.out.println("новый пользователь: " + user);
        userService.create(user);
        return "redirect:/main";
    }


    @PostMapping("/main/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/main";
    }

*/

}
