package com.gz.common;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Lanmu {
    @Id
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
}

@Repository
public interface LanmuRepository extends JpaRepository<Lanmu, Long> {}

@RestController
public class LanmuService {
    @Autowired
    private LanmuRepository lanmuRepository;

    public Lanmu getLanmu(Long id) {
        return lanmuRepository.findById(id).orElse(null);
    }
    
    public Lanmu createLanmu(Lanmu lanmu) {
        return lanmuRepository.save(lanmu);
    }
    
    public void deleteLanmu(Long id) {
        lanmuRepository.deleteById(id);
    }

    public List<Lanmu> getAllLanmus() {
        return lanmuRepository.findAll();
    }
    
    public Lanmu updateLanmu(Lanmu lanmu) {
        return lanmuRepository.save(lanmu);
    }
}
