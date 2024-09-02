// src/test/java/bootcampragma/emazon/domain/usecase/ArticleUseCaseTest.java
package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.exception.article.CategoriesSizeException;
import bootcampragma.emazon.domain.exception.article.DuplicateCategoriesException;
import bootcampragma.emazon.domain.spi.IArticlePersistencePort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
}