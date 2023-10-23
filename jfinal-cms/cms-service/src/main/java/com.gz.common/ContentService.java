package com.gz.common;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Content {
    @Id
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "body")
    private String body;
    
    @Column(name = "author_id")
    private Long authorId;
}

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {}

@RestController
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;

    public Content getContent(Long id) {
        return contentRepository.findById(id).orElse(null);
    }
    
    public Content createContent(Content content) {
        return contentRepository.save(content);
    }
    
    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }
    
    public Content updateContent(Content content) {
        return contentRepository.save(content);
    }
}
