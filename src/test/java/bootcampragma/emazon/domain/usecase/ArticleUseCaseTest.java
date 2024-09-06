package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.exception.article.CategoriesSizeException;
import bootcampragma.emazon.domain.exception.article.DuplicateCategoriesException;
import bootcampragma.emazon.domain.spi.IArticlePersistencePort;
import bootcampragma.emazon.domain.util.CustomArticlePage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArticleUseCaseTest {
    private final IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
    private final ArticleUseCase articleUseCase = new ArticleUseCase(articlePersistencePort);

    @Test
    void saveArticle_WhenCategoriesAreUniqueAndWithinLimit_ShouldSaveArticle() {
        Article article = new Article();
        article.setCategoriesId(List.of(1L, 2L, 3L));

        articleUseCase.saveArticle(article);

        verify(articlePersistencePort, times(1)).saveArticle(article);
    }

    @Test
    void saveArticle_WhenCategoriesAreNotUnique_ShouldThrowDuplicateCategoriesException() {
        Article article = new Article();
        article.setCategoriesId(List.of(1L, 2L, 2L));

        assertThrows(DuplicateCategoriesException.class, () -> articleUseCase.saveArticle(article));

        verify(articlePersistencePort, never()).saveArticle(article);
    }

    @Test
    void saveArticle_WhenCategoriesAreEmpty_ShouldThrowCategoriesSizeException() {
        Article article = new Article();
        article.setCategoriesId(new ArrayList<>());

        assertThrows(CategoriesSizeException.class, () -> articleUseCase.saveArticle(article));

        verify(articlePersistencePort, never()).saveArticle(article);
    }

    @Test
    void saveArticle_WhenCategoriesExceedLimit_ShouldThrowCategoriesSizeException() {
        Article article = new Article();
        article.setCategoriesId(List.of(1L, 2L, 3L, 4L));

        assertThrows(CategoriesSizeException.class, () -> articleUseCase.saveArticle(article));

        verify(articlePersistencePort, never()).saveArticle(article);
    }
    @Test
    void getAllArticle_ShouldReturnValidPage_WhenValidInput() {
        // Arrange
        CustomArticlePage<Article> expectedPage = new CustomArticlePage<>(Collections.singletonList(new Article()), 0, 10, 1L, 1, "name");
        when(articlePersistencePort.getAllArticle(anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(expectedPage);

        // Act
        CustomArticlePage<Article> result = articleUseCase.getAllArticle(0, 10, "asc", "name");

        // Assert
        assertEquals(expectedPage, result);
        verify(articlePersistencePort, times(1)).getAllArticle(0, 10, "asc", "name");
    }

    @Test
    void getAllArticle_ShouldReturnEmptyPage_WhenNoArticlesFound() {
        // Arrange
        CustomArticlePage<Article> emptyPage = new CustomArticlePage<>(Collections.emptyList(), 0, 10, 0L, 0,"name");
        when(articlePersistencePort.getAllArticle(anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(emptyPage);

        // Act
        CustomArticlePage<Article> result = articleUseCase.getAllArticle(0, 10, "asc", "name");

        // Assert
        assertEquals(emptyPage, result);
        verify(articlePersistencePort, times(1)).getAllArticle(0, 10, "asc", "name");
    }

    @Test
    void getAllArticle_ShouldHandleInvalidPageParameters() {
        // Arrange
        when(articlePersistencePort.getAllArticle(anyInt(), anyInt(), anyString(), anyString()))
                .thenThrow(new IllegalArgumentException("Invalid page parameters"));

        // Act & Assert
        try {
            articleUseCase.getAllArticle(-1, -5, "asc", "name");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid page parameters", e.getMessage());
        }

        verify(articlePersistencePort, times(1)).getAllArticle(-1, -5, "asc", "name");
    }

    @Test
    void getAllArticle_ShouldHandleNullSortDirection() {
        // Arrange
        CustomArticlePage<Article> defaultPage = new CustomArticlePage<>(Collections.singletonList(new Article()), 0, 10, 1L, 1,"name");
        when(articlePersistencePort.getAllArticle(anyInt(), anyInt(), eq(null), anyString()))
                .thenReturn(defaultPage);

        // Act
        CustomArticlePage<Article> result = articleUseCase.getAllArticle(0, 10, null, "name");

        // Assert
        assertEquals(defaultPage, result);
        verify(articlePersistencePort, times(1)).getAllArticle(0, 10, null, "name");
    }
}