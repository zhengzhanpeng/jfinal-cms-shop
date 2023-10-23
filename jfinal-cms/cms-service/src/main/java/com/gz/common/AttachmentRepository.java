package com.gz.common;

import com.gz.common.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    List<Attachment> findByContentId(int contentId);
    List<Attachment> findByProductId(int productId);
}
