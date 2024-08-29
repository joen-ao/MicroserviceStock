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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

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
        category = new Category(1L, "Category Name", "Category Description");
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
    @Test
    void getAllCategory_ShouldReturnMappedCategoryList_WhenSortDirectionIsProvided() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String sortDirection = "asc";

        Pageable pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), "name"));

        CategoryEntity categoryEntity1 = new CategoryEntity(1L, "Category 1", "Description 1");
        CategoryEntity categoryEntity2 = new CategoryEntity(2L, "Category 2", "Description 2");

        List<CategoryEntity> categoryEntityList = Arrays.asList(categoryEntity1, categoryEntity2);
        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntityList);

        when(categoryRepository.findAll(pagination)).thenReturn(categoryEntityPage);

        Category category1 = new Category(1L, "Category 1", "Description 1");
        Category category2 = new Category(2L, "Category 2", "Description 2");

        List<Category> expectedCategories = Arrays.asList(category1, category2);

        when(categoryEntityMapper.toCategoryList(categoryEntityList)).thenReturn(expectedCategories);

        // Act
        List<Category> actualCategories = categoryJpaAdapter.getAllCategory(page, size, sortDirection);

        // Assert
        assertEquals(expectedCategories, actualCategories);
        verify(categoryRepository, times(1)).findAll(pagination);
        verify(categoryEntityMapper, times(1)).toCategoryList(categoryEntityList);
    }

    @Test
    void getAllCategory_ShouldReturnMappedCategoryList_WhenSortDirectionIsNullOrEmpty() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String sortDirection = null;

        Pageable pagination = PageRequest.of(page, size);

        CategoryEntity categoryEntity1 = new CategoryEntity(1L, "Category 1", "Description 1");
        CategoryEntity categoryEntity2 = new CategoryEntity(2L, "Category 2", "Description 2");

        List<CategoryEntity> categoryEntityList = Arrays.asList(categoryEntity1, categoryEntity2);
        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntityList);

        when(categoryRepository.findAll(pagination)).thenReturn(categoryEntityPage);

        Category category1 = new Category(1L, "Category 1", "Description 1");
        Category category2 = new Category(2L, "Category 2", "Description 2");

        List<Category> expectedCategories = Arrays.asList(category1, category2);

        when(categoryEntityMapper.toCategoryList(categoryEntityList)).thenReturn(expectedCategories);

        // Act
        List<Category> actualCategories = categoryJpaAdapter.getAllCategory(page, size, sortDirection);

        // Assert
        assertEquals(expectedCategories, actualCategories);
        verify(categoryRepository, times(1)).findAll(pagination);
        verify(categoryEntityMapper, times(1)).toCategoryList(categoryEntityList);
    }
}
