import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public Attachment getAttachment(@RequestParam String id) {
        return attachmentRepository.findById(id).orElse(null);
    }

    @Secured("ROLE_ADMIN")
    public Attachment createAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }

    @Secured("ROLE_ADMIN")
    public void deleteAttachment(@RequestParam String id) {
        attachmentRepository.deleteById(id);
    }
}
