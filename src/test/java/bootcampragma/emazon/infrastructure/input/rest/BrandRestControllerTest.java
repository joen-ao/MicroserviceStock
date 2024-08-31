package bootcampragma.emazon.infrastructure.input.rest;

import bootcampragma.emazon.aplication.dto.BrandRequest;
import bootcampragma.emazon.aplication.handler.IBrandHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class BrandRestControllerTest {

    @InjectMocks
    private BrandRestController brandRestController;

    @Mock
    private IBrandHandler brandHandler;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void saveBrand_ShouldReturnCreated() {
        // Arrange
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Nike");
        brandRequest.setDescription("Sports brand");

        // Act
        ResponseEntity<Void> response = brandRestController.saveBrand(brandRequest);

        // Assert
        verify(brandHandler).saveBrand(brandRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}

