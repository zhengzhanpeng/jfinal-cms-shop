package com.gz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest 
{
    @Autowired
    private UserService userService;
    
    @Autowired
    private AttachmentService attachmentService;
    
    @Test
    public void testUserServiceGetList() {
        List<User> users = userService.getList();
        assertNotNull(users);
    }
    
    @Test
    public void testAttachmentServiceGetList() {
        List<Attachment> attachments = attachmentService.getList(1);
        assertNotNull(attachments);
    }
}
