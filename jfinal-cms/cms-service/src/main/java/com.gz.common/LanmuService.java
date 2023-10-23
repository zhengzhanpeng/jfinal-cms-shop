import org.springframework.stereotype.Service;

@Service
public class LanmuService {
    // Existing code and methods
    
    // JFinal-specific code to be replaced
    // ...
    
    // Spring Boot equivalent code
    // ...
}
```

For the unit tests, you can create a new file `LanmuServiceTest.java` in the same directory (`jfinal-cms/cms-service/src/main/java/com.gz.common/`) to test the `LanmuService` class. The unit tests should cover all the methods and edge cases of the `LanmuService` class.

Here is an example of a unit test for the `LanmuService` class:

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LanmuServiceTest {

    @Autowired
    private LanmuService lanmuService;

    @Test
    public void testLanmuServiceMethod() {
        // Test the functionality of the LanmuService methods
        // ...
    }

    // Additional unit tests for other methods and edge cases
    // ...
}
