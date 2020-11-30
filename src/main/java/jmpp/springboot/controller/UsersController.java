package jmpp.springboot.controller;

import jmpp.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import jmpp.springboot.model.User;
import jmpp.springboot.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/")
    public String hello(ModelMap model) {
        String messages = "Hello!";
        model.addAttribute("messages", messages);
        return "hello";
    }


    @GetMapping(value = "/admin")
    public String allUsers(ModelMap model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("users", listUsers);
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.listAll());
        return "admin";
    }


    @GetMapping("/admin/edit/{id}")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        System.out.println("все роли: " + roleService.listAll());
        System.out.println("текущий пользователь: " + userService.getUser(id));
        model.addAttribute("allRoles", roleService.listAll());
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/admin/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id, @RequestParam(value = "allRoles") String[] roles) {
        System.out.println("новые роли: " + roles);
        user.setRoles(userService.newRoles(roles));
//        System.out.println("обновляем пользователя: " + user);
        userService.update(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("allRoles", roleService.listAll());
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "allRoles") String[] roles) {
        user.setRoles(userService.newRoles(roles));
        userService.create(user);
        return "redirect:/admin";
    }


    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String currentUser(ModelMap model, Authentication autUser) {
        User user = userService.findUserByUsername(autUser.getName());
//        System.out.println("LoggedIn: " + user);
        model.addAttribute("user", user);
        return "user";
    }

//    @ModelAttribute("logoutLink")
////    @GetMapping(value = "logout")
//    @GetMapping("/logout")
//    public String logout() {
//        return "redirect:/";
//    }



}
