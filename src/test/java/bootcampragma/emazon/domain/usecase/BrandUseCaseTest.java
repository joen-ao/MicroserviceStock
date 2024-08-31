package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.infrastructure.exception.brand.BrandAlreadyExistException;

import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
