import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Field> getField(@RequestParam String id) {
        Field field = fieldRepository.findById(id).orElse(null);
        return ResponseEntity.ok(field);
    }

    @Secured("ROLE_ADMIN")
    public ResponseEntity<Field> createField(Field field) {
        Field savedField = fieldRepository.save(field);
        return ResponseEntity.ok(savedField);
    }

    public ResponseEntity<List<Field>> getAllFields() {
        List<Field> fields = fieldRepository.findAll();
        return ResponseEntity.ok(fields);
    }

    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteField(@RequestParam String id) {
        fieldRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
