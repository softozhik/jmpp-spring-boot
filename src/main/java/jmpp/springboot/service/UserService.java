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
    private EntityManager em;
    @Autowired
    private RoleService roleService;

    public void addAdmin() {
        if (findUserByUsername("admin") == null) {
            Set<Role> role = new HashSet<>();
            role.add(roleService.getRole(1L));
            User user = new User("admin", "admin", "admin@mail.11", "admin", role);
            create(user);
        }
    }

    public Set<Role> updateRoles(String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            roleSet.add(roleService.findRoleByName(roleName));
        }
       return roleSet;
    }

    public User newRoles(User user) {
        Set<Role> roleSet = new HashSet<>();
        Iterator<Role> iterator = user.getRoles().iterator();
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
        try{
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
            return query.setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User findUserByUsername(String username) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            return query.setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            return  null;
        }

    }

    @Transactional
    public void update(User changeUser) {
        if (changeUser.getRoles() != null) newRoles(changeUser);
        em.merge(changeUser);
    }

    @Transactional
    public void create(User user) {
        if (user.getRoles() != null) newRoles(user);
        em.persist(user);
    }

    @Transactional
    public void deleteById(Long id) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        User user = query.setParameter("id", id).getSingleResult();
        em.remove(user);
    }

    }
