package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BrandJpaAdapterTest {

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand_handlesNullBrand() {
        Brand brand = null;

        assertThrows(NullPointerException.class, () -> brandJpaAdapter.saveBrand(brand));
    }

    @Test
    void getAllBrand_handlesInvalidSortDirection() {
        int page = 0;
        int size = 10;
        String sortDirection = "INVALID";

        assertThrows(IllegalArgumentException.class, () -> brandJpaAdapter.getAllBrand(page, size, sortDirection));
    }

    @Test
    void getAllBrand_handlesNegativePageNumber() {
        int page = -1;
        int size = 10;
        String sortDirection = "ASC";

        assertThrows(IllegalArgumentException.class, () -> brandJpaAdapter.getAllBrand(page, size, sortDirection));
    }

    @Test
    void getAllBrand_handlesNegativePageSize() {
        int page = 0;
        int size = -10;
        String sortDirection = "ASC";

        assertThrows(IllegalArgumentException.class, () -> brandJpaAdapter.getAllBrand(page, size, sortDirection));
    }
}
