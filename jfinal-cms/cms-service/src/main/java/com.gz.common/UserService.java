import org.springframework.stereotype.Service;

@Service
public class UserService {
    // Existing code and methods
    
    // Spring Boot equivalent code
    // ...
}
```

For the unit tests, you can create a new file `UserServiceTest.java` in the same directory (`jfinal-cms/cms-service/src/main/java/com.gz.common/`) to test the `UserService` class. The unit tests should cover all the methods and edge cases of the `UserService` class.

Here is an example of a unit test for the `UserService` class:

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserServiceMethod() {
        // Test the functionality of the UserService methods
        // ...
    }

    // Additional unit tests for other methods and edge cases
    // ...
}
