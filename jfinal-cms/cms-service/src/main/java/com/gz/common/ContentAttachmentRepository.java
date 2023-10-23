package com.gz.common;

import com.gz.common.model.ContentAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentAttachmentRepository extends JpaRepository<ContentAttachment, Integer> {
    ContentAttachment findByContentIdAndAttachmentId(Integer contentId, Integer attachmentId);
}
