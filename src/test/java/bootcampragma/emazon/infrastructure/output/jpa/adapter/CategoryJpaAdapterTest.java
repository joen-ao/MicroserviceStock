package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.infrastructure.exception.*;
import bootcampragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryJpaAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setName("Valid Name");
        category.setDescription("Valid Description");
    }

    @Test
    void saveCategory_ShouldThrowCategoryAlreadyExistException_WhenCategoryExists() {
        when(categoryRepository.existsByName(category.getName())).thenReturn(true);

        assertThrows(CategoryAlreadyExistException.class, () -> categoryJpaAdapter.saveCategory(category));
    }

    @Test
    void saveCategory_ShouldThrowCategoryNameEmptyException_WhenCategoryNameIsEmpty() {
        category.setName("");

        assertThrows(CategoryNameEmptyException.class, () -> categoryJpaAdapter.saveCategory(category));
    }

    @Test
    void saveCategory_ShouldThrowCategoryDescriptionEmptyException_WhenCategoryDescriptionIsEmpty() {
        category.setDescription("");

        assertThrows(CategoryDescriptionEmptyException.class, () -> categoryJpaAdapter.saveCategory(category));
    }

    @Test
    void saveCategory_ShouldThrowCategoryOversizeNameException_WhenCategoryNameIsTooLong() {
        category.setName("A".repeat(Category.MAX_NAME_LENGTH + 1));

        assertThrows(CategoryOversizeNameException.class, () -> categoryJpaAdapter.saveCategory(category));
    }

    @Test
    void saveCategory_ShouldThrowCategoryOversizeDescriptionException_WhenCategoryDescriptionIsTooLong() {
        category.setDescription("A".repeat(Category.MAX_DESCRIPTION_LENGTH + 1));

        assertThrows(CategoryOversizeDescriptionException.class, () -> categoryJpaAdapter.saveCategory(category));
    }

    @Test
    void saveCategory_ShouldSaveCategory_WhenCategoryIsValid() {
        when(categoryRepository.existsByName(category.getName())).thenReturn(false);
        when(categoryEntityMapper.toCategoryEntity(category)).thenReturn(new CategoryEntity());

        categoryJpaAdapter.saveCategory(category);

        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }
}