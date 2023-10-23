package com.gz.common;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Field {
    @Id
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "value")
    private String value;
}

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {}

@RestController
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    public Field getField(Long id) {
        return fieldRepository.findById(id).orElse(null);
    }
    
    public Field createField(Field field) {
        return fieldRepository.save(field);
    }
    
    public void deleteField(Long id) {
        fieldRepository.deleteById(id);
    }

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }
    
    public Field updateField(Field field) {
        return fieldRepository.save(field);
    }
}
