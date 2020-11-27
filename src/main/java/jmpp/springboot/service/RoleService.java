package jmpp.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jmpp.springboot.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    EntityManager em;

    public List<Role> listAll() {
        List<Role> allRoles;
        allRoles = em.createQuery("select r from Role r").getResultList();
        return allRoles;
    }

    public void create(Long id, String role) {
        em.persist(new Role(id, role));
    }

    public Role getRole(Long id) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.id = :id", Role.class);
        Role role = query.setParameter("id", id).getSingleResult();
        return role;
    }

    public void addRoles() {
        if (getRole(1L) == null) {
            create(1L, "ROLE_ADMIN");
        }
        if (getRole(2L) == null) {
            create(2L, "ROLE_USER");
        }
        if (getRole(3L) == null) {
            create(3L, "ROLE_OVER");
        }
    }

    public Role findRoleByName(String roleName) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.role = :role", Role.class);
        Role role = query.setParameter("role", roleName).getSingleResult();
        return role;
    }

}

