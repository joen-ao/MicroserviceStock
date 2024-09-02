package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

class ArticleJpaAdapterTest {

    @Mock
    private IArticleRepository articleRepository;

    @Mock
    private ArticleEntityMapper articleEntityMapper;

    @InjectMocks
    private ArticleJpaAdapter articleJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveArticle_savesArticleSuccessfully() {
        Article article = new Article();
        when(articleEntityMapper.toArticleEntity(article)).thenReturn(new ArticleEntity());

        articleJpaAdapter.saveArticle(article);

        verify(articleRepository, times(1)).save(any(ArticleEntity.class));
    }

    @Test
    void saveArticle_handlesNullArticle() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            articleJpaAdapter.saveArticle(null);
        });

        // Verify that save method was never called
        verify(articleRepository, never()).save(any());
    }

    @Test
    void saveArticle_ShouldSaveArticle_WhenValidArticleProvided() {
        // Arrange
        Article article = new Article();
        ArticleEntity articleEntity = new ArticleEntity();
        when(articleEntityMapper.toArticleEntity(any(Article.class))).thenReturn(articleEntity);

        // Act
        articleJpaAdapter.saveArticle(article);

        // Assert
        verify(articleEntityMapper).toArticleEntity(article);
        verify(articleRepository).save(articleEntity);
    }

    @Test
    void saveArticle_ShouldNotSave_WhenExceptionThrownByMapper() {
        // Arrange
        Article article = new Article();
        when(articleEntityMapper.toArticleEntity(any(Article.class))).thenThrow(new RuntimeException("Mapper exception"));

        // Act & Assert
        try {
            articleJpaAdapter.saveArticle(article);
        } catch (RuntimeException e) {
            // Expected exception
        }

        verify(articleEntityMapper).toArticleEntity(article);
        // Ensure that save method is not called if mapper throws an exception
        verify(articleRepository, times(0)).save(any(ArticleEntity.class));
    }
}