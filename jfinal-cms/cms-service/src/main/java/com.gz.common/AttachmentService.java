package com.gz.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AttachmentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createAttachment(@RequestParam String name, @RequestParam String url) {
        Attachment attachment = new Attachment();
        attachment.setName(name);
        attachment.setUrl(url);
        attachmentRepository.save(attachment);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getAttachment(@RequestParam Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElse(null);
        if (attachment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attachment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAttachment(@RequestParam Long id) {
        if (!attachmentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        attachmentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
