package com.gz.common;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class User {
    @Id
    private Long id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
+    private String password;
+    
+    @Column(name = "email")
+    private String email;
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}

@RestController
public class UserService {
    @Autowired
    private UserRepository userRepository;

+    public User getUser(Long id) {
+        return userRepository.findById(id).orElse(null);
+    }
+    
+    public User createUser(User user) {
+        return userRepository.save(user);
+    }
+    
+    public void deleteUser(Long id) {
+        userRepository.deleteById(id);
+    }
+
+    public List<User> getAllUsers() {
+        return userRepository.findAll();
+    }
+    
+    public User updateUser(User user) {
+        return userRepository.save(user);
+    }
}
