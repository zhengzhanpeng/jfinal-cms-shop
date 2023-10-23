package com.gz.common;

import com.gz.common.model.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public Page<Content> getPages(int pageNum, int pageSize, int lanmuId){
        return contentRepository.findByLanmuIdAndGzDeleteFalse(PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "sortNum", "id")), lanmuId);
    }
    public Page<Content> getPages(int pageNum, int pageSize, String where){
        return contentRepository.findByWhereAndGzDeleteFalse(where, PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "sortNum", "id")));
    }
    public Content getOneContent(int lanmuId){
        return contentRepository.findFirstByLanmuId(lanmuId);
    }
    public Content getContent(int id){
        return contentRepository.findById(id).orElse(null);
    }
    public List<Content> getContentList(int lanmuId){
        return contentRepository.findByLanmuIdOrderBySortNumDescIdDesc(lanmuId);
    }
}
