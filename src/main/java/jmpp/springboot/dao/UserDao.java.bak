package jmpp.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jmpp.springboot.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

}

