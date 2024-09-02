package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.request.ArticleRequest;
import bootcampragma.emazon.aplication.dto.request.CategoryRequest;
import bootcampragma.emazon.aplication.mapper.request.ArticleRequestMapper;
import bootcampragma.emazon.aplication.mapper.request.CategoryRequestMapper;
import bootcampragma.emazon.domain.api.IArticleServicePort;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ArticleHandlerTest {

    @Mock
    private IArticleServicePort articleServicePort;

    @Mock
    private ArticleRequestMapper articleRequestMapper;

    @InjectMocks
    private ArticleHandler articleHandler;


    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveCategory_ShouldCallSaveCategoryOnServicePort() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Category Name");
        categoryRequest.setDescription("Category Description");

        when(categoryRequestMapper.toRequest(categoryRequest)).thenReturn(new Category(1L, "Category Name", "Category Description"));

        categoryHandler.saveCategory(categoryRequest);

        verify(categoryRequestMapper, times(1)).toRequest(categoryRequest);
        verify(categoryServicePort, times(1)).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_WithNullRequest_ShouldThrowException() {
        CategoryRequest categoryRequest = null;

        assertThrows(IllegalArgumentException.class, () -> categoryHandler.saveCategory(categoryRequest));
    }

    @Test
    void saveArticle_savesArticleSuccessfully() {
        ArticleRequest articleRequest = new ArticleRequest();
        Article article = new Article(); // Assuming Article is the return type of toRequest method
        when(articleRequestMapper.toRequest(articleRequest)).thenReturn(article);

        articleHandler.saveArticle(articleRequest);

        verify(articleServicePort).saveArticle(article);
    }

    @Test
    void saveArticle_throwsExceptionWhenArticleRequestIsNull() {
        assertThrows(IllegalArgumentException.class, () -> articleHandler.saveArticle(null));
    }
}