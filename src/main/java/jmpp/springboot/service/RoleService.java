package jmpp.springboot.service;

import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;
        import jmpp.springboot.dao.RoleDao;
        import jmpp.springboot.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleDao roleDao;

    @Autowired
    EntityManager em;

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public Role getOne(Long id) {
        return roleDao.getOne(id);
    }

    public void addRoles() {
        if (!roleDao.existsById(1L)) {
            roleDao.save(new Role(1L, "ROLE_ADMIN"));
        }
        if (!roleDao.existsById(2L)) {
            roleDao.save(new Role(2L, "ROLE_USER"));
        }
        if (!roleDao.existsById(3L)) {
//            roleDao.save(new Role(3L, "ROLE_OVER"));
            em.persist(new Role(3L, "ROLE_OVER"));
        }
    }


}

