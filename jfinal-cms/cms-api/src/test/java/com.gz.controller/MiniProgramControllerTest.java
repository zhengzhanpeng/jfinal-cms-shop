import com.jfinal.kit.Kv;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.wxaapp.api.WxaUserApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MiniProgramControllerTest {

    @Mock
    private WxaUserApi wxaUserApi;

    @InjectMocks
    private MiniProgramController miniProgramController;

    @Test
    public void testLogin() {
        String code = "testCode";
        ApiResult apiResult = new ApiResult();
        when(wxaUserApi.getSessionKey(code)).thenReturn(apiResult);

        ResponseEntity<Kv> response = miniProgramController.login(code);

        assertEquals(apiResult.getJson(), response.getBody());
    }

    @Test
    public void testInfo() {
        String signature = "testSignature";
        String rawData = "testRawData";
        String encryptedData = "testEncryptedData";
        String iv = "testIv";
        String sessionId = "testSessionId";
        ApiResult apiResult = new ApiResult();
        when(wxaUserApi.getUserInfo(sessionId, encryptedData, iv)).thenReturn(apiResult);

        ResponseEntity<Kv> response = miniProgramController.info(signature, rawData, encryptedData, iv, sessionId);

        assertEquals(apiResult.getJson(), response.getBody());
    }
}
