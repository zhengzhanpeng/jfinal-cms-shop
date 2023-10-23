import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class LanmuService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LanmuService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Lanmu> findById(int id) {
        String sql = "SELECT * FROM lanmu WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new LanmuRowMapper()));
    }

    public List<Lanmu> findAll() {
        String sql = "SELECT * FROM lanmu";
        return jdbcTemplate.query(sql, new LanmuRowMapper());
    }
    
    public int save(Lanmu lanmu) {
        String sql = "INSERT INTO lanmu (name, description) VALUES (?, ?)";
        return jdbcTemplate.update(sql, lanmu.getName(), lanmu.getDescription());
    }
    
    public int update(Lanmu lanmu) {
        String sql = "UPDATE lanmu SET name = ?, description = ? WHERE id = ?";
        return jdbcTemplate.update(sql, lanmu.getName(), lanmu.getDescription(), lanmu.getId());
    }
    
    public int delete(int id) {
        String sql = "DELETE FROM lanmu WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
