import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUser(@RequestParam String id) {
        User user = userRepository.findById(id).orElse(null);
        return ResponseEntity.ok(user);
    }

    @Secured("ROLE_ADMIN")
    public ResponseEntity<User> createUser(User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteUser(@RequestParam String id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
