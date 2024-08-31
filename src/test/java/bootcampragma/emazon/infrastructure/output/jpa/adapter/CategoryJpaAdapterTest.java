package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;




@ExtendWith(MockitoExtension.class)
class CategoryJpaAdapterTest {


    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
void saveCategory_handlesNullCategory() {
    Category category = null;

    assertThrows(NullPointerException.class, () -> categoryJpaAdapter.saveCategory(category));
}

@Test
void getAllCategory_handlesInvalidSortDirection() {
    int page = 0;
    int size = 10;
    String sortDirection = "INVALID";

    assertThrows(IllegalArgumentException.class, () -> categoryJpaAdapter.getAllCategory(page, size, sortDirection));
}

@Test
void getAllCategory_handlesNegativePageNumber() {
    int page = -1;
    int size = 10;
    String sortDirection = "ASC";

    assertThrows(IllegalArgumentException.class, () -> categoryJpaAdapter.getAllCategory(page, size, sortDirection));
}

@Test
void getAllCategory_handlesNegativePageSize() {
    int page = 0;
    int size = -10;
    String sortDirection = "ASC";

    assertThrows(IllegalArgumentException.class, () -> categoryJpaAdapter.getAllCategory(page, size, sortDirection));
}


}

