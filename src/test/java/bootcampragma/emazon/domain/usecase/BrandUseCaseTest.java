package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.infrastructure.exception.brand.BrandAlreadyExistException;

import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class BrandUseCaseTest {

    @Mock
    private IBrandRepository brandRepository;

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

        when(brandRepository.existsByName(brand.getName())).thenReturn(false);

        brandUseCase.saveBrand(brand);

        verify(brandRepository, times(1)).existsByName(brand.getName());
        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void saveBrand_WhenBrandExists_ShouldThrowException() {
        Brand brand = new Brand();
        brand.setName("Nike");

        when(brandRepository.existsByName(brand.getName())).thenReturn(true);

        assertThrows(BrandAlreadyExistException.class, () -> brandUseCase.saveBrand(brand));

        verify(brandRepository, times(1)).existsByName(brand.getName());
        verify(brandPersistencePort, never()).saveBrand(brand);
    }
    @Test
    void getAllBrand_ShouldReturnPage_WhenCalled() {
        // Arrange
        List<Brand> brands = new ArrayList<>();
        long totalElements = 100;  // Adjust this value according to the total elements expected
        int totalPages = 10;       // Adjust this value according to the total pages expected
        CustomPage<Brand> expectedPage = new CustomPage<>(brands, 0, 10, totalElements, totalPages);

        when(brandPersistencePort.getAllBrand(0, 10, "ASC")).thenReturn(expectedPage);

        // Act
        CustomPage<Brand> resultPage = brandUseCase.getAllBrand(0, 10, "ASC");

        // Assert
        assertEquals(expectedPage, resultPage);
    }
}
