import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Content> getContent(@RequestParam String id) {
        Content content = contentRepository.findById(id).orElse(null);
        return ResponseEntity.ok(content);
    }

    @Secured("ROLE_ADMIN")
    public ResponseEntity<Content> createContent(Content content) {
        Content savedContent = contentRepository.save(content);
        return ResponseEntity.ok(savedContent);
    }

    public ResponseEntity<List<Content>> getAllContents() {
        List<Content> contents = contentRepository.findAll();
        return ResponseEntity.ok(contents);
    }

    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteContent(@RequestParam String id) {
        contentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
