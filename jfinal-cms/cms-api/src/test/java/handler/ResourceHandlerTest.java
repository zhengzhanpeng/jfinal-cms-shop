import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResourceHandlerTest {

    @Mock
    private Handler nextHandler;

    @InjectMocks
    private ResourceHandler resourceHandler;

    @Test
    public void testHandle_withImageExtension() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        boolean[] booleans = new boolean[1];

        resourceHandler.handle("test.jpg", request, response, booleans);

        assertTrue(booleans[0]);
        verify(nextHandler, never()).handle(anyString(), any(), any(), anyBoolean());
    }

    @Test
    public void testHandle_withoutImageExtension() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        boolean[] booleans = new boolean[1];

        resourceHandler.handle("test.txt", request, response, booleans);

        assertFalse(booleans[0]);
        verify(nextHandler).handle(anyString(), any(), any(), anyBoolean());
    }
}
