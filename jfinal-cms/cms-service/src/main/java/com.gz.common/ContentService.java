import org.springframework.stereotype.Service;

@Service
public class ContentService {
    // Existing code and methods
    
    // JFinal-specific code to be replaced
    // ...
    
    // Spring Boot equivalent code
    // ...
}
```

The JFinal-specific code in `ContentService.java` should be identified and replaced with the equivalent Spring Boot code. The relevant imports should also be updated to use Spring Boot annotations and classes.

For the unit tests, you can create a new file `ContentServiceTest.java` in the same directory (`jfinal-cms/cms-service/src/main/java/com.gz.common/`) to test the `ContentService` class. The unit tests should cover all the methods and edge cases of the `ContentService` class.

Here is an example of a unit test for the `ContentService` class:

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContentServiceTest {

    @Autowired
    private ContentService contentService;

    @Test
    public void testContentServiceMethod() {
        // Test the functionality of the ContentService methods
        // ...
    }

    // Additional unit tests for other methods and edge cases
    // ...
}
