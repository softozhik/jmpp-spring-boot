package jmpp.springboot.controller;

import jmpp.springboot.model.User;
import jmpp.springboot.service.RoleService;
import jmpp.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/api")
    public List<User> listUsers() {
        List<User> listUsers = userService.listAll();
        return listUsers;
    }
}
