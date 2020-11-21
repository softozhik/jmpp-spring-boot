package jmpp.springboot.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jmpp.springboot.model.Role;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    Role findRoleByRole(String role);
    List<String> findRoleById(Long id);
}
