package jmpp.springboot.controller;

import jmpp.springboot.model.User;
import jmpp.springboot.service.RoleService;
import jmpp.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/api")
    public List<User> listUsers() {
        List<User> listUsers = userService.listAll();
        return listUsers;
    }

    @GetMapping("/api/{id")
    public User getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return user;
    }

    @PostMapping("/api/edit/{id}")
    public User update(@RequestBody User user, @PathVariable Long id) {
        userService.update(user);
        return user;
    }

    @PostMapping("/api")
    public User create(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    @PostMapping("api/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "deleted user whith id " + id;
    }

}
