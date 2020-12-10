package jmpp.springboot.controller;

import jmpp.springboot.model.Role;
import jmpp.springboot.model.User;
import jmpp.springboot.service.RoleService;
import jmpp.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

/*
    @GetMapping("/api/allroles")
    public Set<Role> allRoles() {
        return new HashSet<Role>(roleService.listAll());
    }
*/

    @GetMapping("/api/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return user;
    }

/*
    @GetMapping("/api/allroles")
    public List<Role> listRoles() {
//        List<Role> listRoles = roleService.listAll();
        return listRoles();
    }
*/

    @PostMapping("/api/edit")
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
