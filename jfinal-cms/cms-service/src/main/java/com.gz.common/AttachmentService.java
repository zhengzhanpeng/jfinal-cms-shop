import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class AttachmentService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AttachmentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Attachment> findById(int id) {
        String sql = "SELECT * FROM attachment WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new AttachmentRowMapper()));
    }

    public List<Attachment> findAll() {
        String sql = "SELECT * FROM attachment";
        return jdbcTemplate.query(sql, new AttachmentRowMapper());
    }
    
    public int save(Attachment attachment) {
        String sql = "INSERT INTO attachment (name, path) VALUES (?, ?)";
        return jdbcTemplate.update(sql, attachment.getName(), attachment.getPath());
    }
    
    public int update(Attachment attachment) {
        String sql = "UPDATE attachment SET name = ?, path = ? WHERE id = ?";
        return jdbcTemplate.update(sql, attachment.getName(), attachment.getPath(), attachment.getId());
    }
    
    public int delete(int id) {
        String sql = "DELETE FROM attachment WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
