import org.springframework.stereotype.Service;

@Service
public class ProductService {
    // Existing code and methods
    
    // Spring Boot equivalent code
    // ...
}
```

For the unit tests, you can create a new file `ProductServiceTest.java` in the same directory (`jfinal-cms/cms-service/src/main/java/com.gz.common/`) to test the `ProductService` class. The unit tests should cover all the methods and edge cases of the `ProductService` class.

Here is an example of a unit test for the `ProductService` class:

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testProductServiceMethod() {
        // Test the functionality of the ProductService methods
        // ...
    }

    // Additional unit tests for other methods and edge cases
    // ...
}
