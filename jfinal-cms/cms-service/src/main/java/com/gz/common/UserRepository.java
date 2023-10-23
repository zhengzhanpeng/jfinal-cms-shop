package com.gz.common;

import com.gz.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByStatusNot(String status);
    User findByUsernameOrMobileOrEmail(String username, String mobile, String email);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByMobile(String mobile);
    User findByToken(String token);
    List<User> findByRole(String role);
}
