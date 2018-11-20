package chapter45.dao;

import chapter45.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
