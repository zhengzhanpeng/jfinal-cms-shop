import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> getProduct(@RequestParam String id) {
        Product product = productRepository.findById(id).orElse(null);
        return ResponseEntity.ok(product);
    }

    @Secured("ROLE_ADMIN")
    public ResponseEntity<Product> createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteProduct(@RequestParam String id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
