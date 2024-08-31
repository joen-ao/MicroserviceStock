package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.BrandRequest;
import bootcampragma.emazon.aplication.mapper.BrandRequestMapper;
import bootcampragma.emazon.domain.api.IBrandServicePort;
import bootcampragma.emazon.domain.entity.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BrandHandlerTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private BrandRequestMapper brandRequestMapper;

    @InjectMocks
    private BrandHandler brandHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand_ShouldCallSaveBrandOnServicePort() {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Brand Name");
        brandRequest.setDescription("Brand Description");

        when(brandRequestMapper.toRequest(brandRequest)).thenReturn(new Brand(1L, "Brand Name", "Brand Description"));

        brandHandler.saveBrand(brandRequest);

        verify(brandRequestMapper, times(1)).toRequest(brandRequest);
        verify(brandServicePort, times(1)).saveBrand(any(Brand.class));
    }

    @Test
    void saveBrand_WithNullRequest_ShouldThrowException() {
        BrandRequest brandRequest = null;

        assertThrows(IllegalArgumentException.class, () -> brandHandler.saveBrand(brandRequest));
    }
}