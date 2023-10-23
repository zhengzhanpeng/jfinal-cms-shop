import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class ContentService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Content> findById(int id) {
        String sql = "SELECT * FROM content WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new ContentRowMapper()));
    }

    public List<Content> findAll() {
        String sql = "SELECT * FROM content";
        return jdbcTemplate.query(sql, new ContentRowMapper());
    }
    
    public int save(Content content) {
        String sql = "INSERT INTO content (title, body) VALUES (?, ?)";
        return jdbcTemplate.update(sql, content.getTitle(), content.getBody());
    }
    
    public int update(Content content) {
        String sql = "UPDATE content SET title = ?, body = ? WHERE id = ?";
        return jdbcTemplate.update(sql, content.getTitle(), content.getBody(), content.getId());
    }
    
    public int delete(int id) {
        String sql = "DELETE FROM content WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
