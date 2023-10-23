package com.gz.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ContentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ContentRepository contentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createContent(@RequestParam String title, @RequestParam String body) {
        Content content = new Content();
        content.setTitle(title);
        content.setBody(body);
        contentRepository.save(content);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getContent(@RequestParam Long id) {
        Content content = contentRepository.findById(id).orElse(null);
        if (content == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(content);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteContent(@RequestParam Long id) {
        if (!contentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
