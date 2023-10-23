--- 
+++ 
@@ -0,0 +1,45 @@
+package com.gz.common;
+
+import org.springframework.beans.factory.annotation.Autowired;
+import org.springframework.stereotype.Service;
+import org.springframework.web.bind.annotation.RequestParam;
+import org.springframework.http.ResponseEntity;
+import org.springframework.security.access.prepost.PreAuthorize;
+import javax.persistence.EntityManager;
+import javax.persistence.PersistenceContext;
+
+@Service
+public class LanmuService {
+
+    @PersistenceContext
+    private EntityManager entityManager;
+
+    @Autowired
+    private LanmuRepository lanmuRepository;
+
+    @PreAuthorize("hasRole('ADMIN')")
+    public ResponseEntity<?> createLanmu(@RequestParam String name, @RequestParam String description) {
+        Lanmu lanmu = new Lanmu();
+        lanmu.setName(name);
+        lanmu.setDescription(description);
+        lanmuRepository.save(lanmu);
+        return ResponseEntity.ok().build();
+    }
+
+    public ResponseEntity<?> getLanmu(@RequestParam Long id) {
+        Lanmu lanmu = lanmuRepository.findById(id).orElse(null);
+        if (lanmu == null) {
+            return ResponseEntity.notFound().build();
+        }
+        return ResponseEntity.ok(lanmu);
+    }
+
+    @PreAuthorize("hasRole('ADMIN')")
+    public ResponseEntity<?> deleteLanmu(@RequestParam Long id) {
+        if (!lanmuRepository.existsById(id)) {
+            return ResponseEntity.notFound().build();
+        }
+        lanmuRepository.deleteById(id);
+        return ResponseEntity.ok().build();
+    }
+}
