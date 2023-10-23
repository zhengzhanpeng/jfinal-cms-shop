package com.gz.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class FieldService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FieldRepository fieldRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createField(@RequestParam String name, @RequestParam String type) {
        Field field = new Field();
        field.setName(name);
        field.setType(type);
        fieldRepository.save(field);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getField(@RequestParam Long id) {
        Field field = fieldRepository.findById(id).orElse(null);
        if (field == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(field);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteField(@RequestParam Long id) {
        if (!fieldRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        fieldRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
