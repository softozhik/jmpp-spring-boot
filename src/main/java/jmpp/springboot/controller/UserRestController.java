package jmpp.springboot.controller;

import jmpp.springboot.model.User;
import jmpp.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/api")
    public List<User> listUsers() {
        List<User> listUsers = userService.listAll();
        return listUsers;
    }

    @GetMapping("/api/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return user;
    }

    @PutMapping("/api/edit")
    public User update(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @PostMapping("/api")
    public User create(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    @DeleteMapping("/api/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "deleted user whith id " + id;
    }

}
