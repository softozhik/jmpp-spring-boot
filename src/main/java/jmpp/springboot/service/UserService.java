package jmpp.springboot.service;

import jmpp.springboot.dao.RoleDao;
import jmpp.springboot.dao.UserDao;
import jmpp.springboot.model.Role;
import jmpp.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService  {

    @PersistenceUnit
    EntityManagerFactory emf;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Transactional
    public void addAdmin() {
        if (!userDao.existsById(1L)) {
            Set<Role> role = new HashSet<>();
            role.add(roleDao.getOne(1L));
            User user = new User("admin", "admin", "admin", "admin@mail.11", role);
            System.out.println("Add ADMIN" + user);
            userDao.save(user);
        }
    }

    public List<User> listAll() {
        return (List<User>) userDao.findAll();
    }

    public User getUser(Long id) {
        System.out.println(userDao.getOne(id));
        return userDao.getOne(id);
    }

    public User findUserByUsername(String username) {
        System.out.println(userDao.findUserByUsername(username));
        return userDao.findUserByUsername(username);
    }

    public Set<Role> newRoles(List roles) {
        Set<Role> roleSet = new HashSet<>(roles);
//        for (roleName : roles) {
//            roleSet.add(roleDao.findRoleByRole(roleName));
//        }
        return roleSet;
    }

    @Transactional
    public void update(Long id, User changeUser) {
        User updateUser = getUser(id);
        updateUser.setUsername(changeUser.getUsername());
        updateUser.setName(changeUser.getName());
        updateUser.setEmail(changeUser.getEmail());
        updateUser.setPassword(changeUser.getPassword());
        updateUser.setRoles(changeUser.getRoles());
    }

}
