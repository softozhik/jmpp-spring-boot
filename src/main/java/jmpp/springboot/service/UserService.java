package jmpp.springboot.service;

import jmpp.springboot.model.Role;
import jmpp.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService  {

    @Autowired
    EntityManager em;
    @Autowired
    private RoleService roleService;

    @Transactional
    public void addAdmin() {
        if (getUser(1L) == null) {
            Set<Role> role = new HashSet<>();
            role.add(roleService.getRole(1L));
            User user = new User("admin", "admin", "admin", "admin@mail.11", role);
//            System.out.println("Add ADMIN" + user);
            create(user);
        }
    }

    public User newRoles(User user) {
        /*
        System.out.println("new roles: " + roles);
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            roleSet.add(roleService.findRoleByName(roleName));
        }
        */
        Set<Role> roleSet = new HashSet<>();
        Iterator iterator = user.getRoles().iterator();
        while (iterator.hasNext()) {
            roleSet.add(roleService.findRoleByName(iterator.next().toString()));
        }
        user.setRoles(roleSet);
        return user;
    }

    public List<User> listAll() {
        List<User> allUsers;
        allUsers = em.createQuery("select u from User u").getResultList();
        return allUsers;
    }

    public User getUser(Long id) {
//        System.out.println(userDao.getOne(id));
//        return userDao.getOne(id);
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        User user = query.setParameter("id", id).getSingleResult();
        return user;
    }

    public User findUserByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        User user = query.setParameter("username", username).getSingleResult();
//        System.out.println("---------------------\n findUserByUsername" + user + "\n---------------------");
        return user;
    }

    @Transactional
    public void update(User changeUser) {
        newRoles(changeUser);
        em.merge(changeUser);

    }

    public void create(User user) {
        newRoles(user);
        em.persist(user);
    }

    public void deleteById(Long id) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        User user = query.setParameter("id", id).getSingleResult();
        em.remove(user);
    }

    }
