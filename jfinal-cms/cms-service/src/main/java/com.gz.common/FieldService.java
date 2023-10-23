import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class FieldService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FieldService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Field> findById(int id) {
        String sql = "SELECT * FROM field WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new FieldRowMapper()));
    }

    public List<Field> findAll() {
        String sql = "SELECT * FROM field";
        return jdbcTemplate.query(sql, new FieldRowMapper());
    }
    
    public int save(Field field) {
        String sql = "INSERT INTO field (name, type) VALUES (?, ?)";
        return jdbcTemplate.update(sql, field.getName(), field.getType());
    }
    
    public int update(Field field) {
        String sql = "UPDATE field SET name = ?, type = ? WHERE id = ?";
        return jdbcTemplate.update(sql, field.getName(), field.getType(), field.getId());
    }
    
    public int delete(int id) {
        String sql = "DELETE FROM field WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
