package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.exception.brand.BrandNotFoundException;
import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.exception.brand.BrandAlreadyExistException;

import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

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
    void findById_ShouldThrowBrandNotFoundException_WhenBrandDoesNotExist() {
        // Arrange
        Long brandId = 1L;
        when(brandPersistencePort.findById(brandId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BrandNotFoundException.class, () -> brandUseCase.findById(brandId));
    }
    @Test
    void findById_ShouldReturnBrand_WhenBrandExists() {
        // Arrange
        Long brandId = 1L;
        Brand expectedBrand = new Brand();
        expectedBrand.setId(brandId);
        when(brandPersistencePort.findById(brandId)).thenReturn(Optional.of(expectedBrand));

        // Act
        Brand result = brandUseCase.findById(brandId);

        // Assert
        assertEquals(expectedBrand, result);
    }

    @Test
    void saveBrand_WhenBrandDoesNotExist_ShouldSaveBrand() {
        Brand brand = new Brand();
        brand.setName("Nike");

        when(brandPersistencePort.findByName(brand.getName())).thenReturn(Optional.empty());

        brandUseCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).findByName(brand.getName());
        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void saveBrand_WhenBrandExists_ShouldThrowException() {
        Brand brand = new Brand();
        brand.setName("Nike");

        when(brandPersistencePort.findByName(brand.getName())).thenReturn(Optional.of(new Brand()));

        assertThrows(BrandAlreadyExistException.class, () -> brandUseCase.saveBrand(brand));

        verify(brandPersistencePort, times(1)).findByName(brand.getName());
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
