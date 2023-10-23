package com.gz.common;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Attachment {
    @Id
    private Long id;
    
    @Column(name = "file_name")
    private String fileName;
    
    @Column(name = "file_path")
    private String filePath;
    
    @Column(name = "file_size")
    private Long fileSize;
    
    @Column(name = "upload_date")
    private Date uploadDate;
    
    @Column(name = "uploader_id")
    private Long uploaderId;
}

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {}

@RestController
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    public Attachment getAttachment(Long id) {
        return attachmentRepository.findById(id).orElse(null);
    }
    
    public Attachment createAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }
    
    public void deleteAttachment(Long id) {
        attachmentRepository.deleteById(id);
    }

    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }
    
    public Attachment updateAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }
}
