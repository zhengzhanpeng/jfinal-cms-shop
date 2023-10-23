import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper()));
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
    
    public int save(User user) {
        String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
    }
    
    public int update(User user) {
        String sql = "UPDATE user SET name = ?, email = ?, password = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getId());
    }
    
    public int delete(int id) {
+        String sql = "DELETE FROM user WHERE id = ?";
+        return jdbcTemplate.update(sql, id);
+    }
}
