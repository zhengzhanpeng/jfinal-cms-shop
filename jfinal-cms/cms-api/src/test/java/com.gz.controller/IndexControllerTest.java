import com.gz.common.ProductService;
import com.gz.common.model.Ad;
import com.gz.common.model.Product;
import com.gz.utils.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IndexControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private IndexController indexController;

    @Test
    public void testIndex() {
        ResponseEntity<String> response = indexController.index();
        assertEquals("index", response.getBody());
    }

    @Test
    public void testGetAdList() {
        Ad ad = new Ad();
        List<Ad> adList = Collections.singletonList(ad);
        when(productService.getAdList()).thenReturn(adList);

        ResponseEntity<String> response = indexController.getAdList();
        assertEquals(Response.responseJson(0,"成功",adList), response.getBody());
    }

    @Test
    public void testGetProductPage() {
        int pageNum = 1;
        int pageSize = 10;
        Product product = new Product();
        List<Product> productList = Collections.singletonList(product);
        when(productService.getProductPage(pageNum, pageSize, null)).thenReturn(productList);

        ResponseEntity<String> response = indexController.getProductPage(pageNum, pageSize);
        assertEquals(Response.responseJson(0,"成功",productList), response.getBody());
    }

    @Test
    public void testGetCategorys() {
        List<String> categoryList = Collections.singletonList("category");
        when(productService.getCategoryList()).thenReturn(categoryList);

        ResponseEntity<String> response = indexController.getCategorys();
        assertEquals(Response.responseJson(0,"成功",categoryList), response.getBody());
    }

    @Test
    public void testGetProduct() {
        int id = 1;
        Product product = new Product();
        when(productService.getProduct(id)).thenReturn(product);

        ResponseEntity<String> response = indexController.getProduct(id);
        assertEquals(Response.responseJson(0,"成功",product), response.getBody());
    }

    @Test
    public void testGetSpecification() {
        int productId = 1;
        String specification = "specification";
        when(productService.getSpecification(productId)).thenReturn(specification);

        ResponseEntity<String> response = indexController.getSpecification(productId);
        assertEquals(Response.responseJson(0,"成功",specification), response.getBody());
    }
}
