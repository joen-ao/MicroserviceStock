package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.exception.brand.BrandAlreadyExistException;
import bootcampragma.emazon.domain.exception.category.InvalidSortDirectionException;
import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.domain.util.CustomPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand_WhenBrandDoesNotExist_ShouldSaveBrand() {
        Brand brand = new Brand();
        brand.setName("Nike");

        when(brandPersistencePort.findByName(brand.getName())).thenReturn(false);

        brandUseCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).findByName(brand.getName());
        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void saveBrand_WhenBrandExists_ShouldThrowException() {
        Brand brand = new Brand();
        brand.setName("Nike");

        when(brandPersistencePort.findByName(brand.getName())).thenReturn(true);

        assertThrows(BrandAlreadyExistException.class, () -> brandUseCase.saveBrand(brand));

        verify(brandPersistencePort, times(1)).findByName(brand.getName());
        verify(brandPersistencePort, never()).saveBrand(brand);
    }

    @Test
    void getAllBrand_ShouldReturnPage_WhenCalled() {
        List<Brand> brands = new ArrayList<>();
        long totalElements = 100;
        int totalPages = 10;
        CustomPage<Brand> expectedPage = new CustomPage<>();

        when(brandPersistencePort.getAllBrand(0, 10, "ASC")).thenReturn(expectedPage);

        CustomPage<Brand> resultPage = brandUseCase.getAllBrand(0, 10, "ASC");

        assertEquals(expectedPage, resultPage);
    }

    @Test
    void getAllBrand_ShouldThrowException_WhenPageIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> brandUseCase.getAllBrand(-1, 10, "ASC"));
    }

    @Test
    void getAllBrand_ShouldThrowException_WhenSortDirectionIsInvalid() {
        assertThrows(InvalidSortDirectionException.class, () -> brandUseCase.getAllBrand(0, 10, "INVALID"));
    }

    @Test
    void getAllBrand_ShouldThrowException_WhenSizeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> brandUseCase.getAllBrand(0, null, "ASC"));
    }

    @Test
    void getAllBrand_ShouldThrowException_WhenSizeIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> brandUseCase.getAllBrand(0, -1, "ASC"));
    }
}