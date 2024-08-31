package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.infrastructure.output.jpa.entity.BrandEntity;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.BrandEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class BrandJpaAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private BrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;

    private Brand brand;

    @BeforeEach
    void setUp() {
        brand = new Brand(1L, "Brand Name", "Brand Description");
    }

    @Test
    void saveBrand_ShouldSaveBrand_WhenBrandIsValid() {
        BrandEntity brandEntity = new BrandEntity();
        when(brandEntityMapper.toBrandEntity(brand)).thenReturn(brandEntity);

        brandJpaAdapter.saveBrand(brand);

        verify(brandRepository, times(1)).save(any(BrandEntity.class));
    }


}