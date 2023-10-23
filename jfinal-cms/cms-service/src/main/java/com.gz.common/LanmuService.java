import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Service
public class LanmuService {

    @Autowired
    private LanmuRepository lanmuRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Lanmu> getLanmu(@RequestParam String id) {
        Lanmu lanmu = lanmuRepository.findById(id).orElse(null);
        return ResponseEntity.ok(lanmu);
    }

    @Secured("ROLE_ADMIN")
    public ResponseEntity<Lanmu> createLanmu(Lanmu lanmu) {
        Lanmu savedLanmu = lanmuRepository.save(lanmu);
        return ResponseEntity.ok(savedLanmu);
    }

    public ResponseEntity<List<Lanmu>> getAllLanmus() {
        List<Lanmu> lanmus = lanmuRepository.findAll();
        return ResponseEntity.ok(lanmus);
    }

    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteLanmu(@RequestParam String id) {
        lanmuRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
