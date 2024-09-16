package bootcampragma.emazon.domain.usecase;
import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.exception.article.CategoriesNullException;
import bootcampragma.emazon.domain.exception.article.CategoriesSizeException;
import bootcampragma.emazon.domain.exception.article.DuplicateCategoriesException;
import bootcampragma.emazon.domain.exception.brand.BrandNotFoundException;
import bootcampragma.emazon.domain.spi.IArticlePersistencePort;
import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;
import bootcampragma.emazon.domain.util.CustomPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.List;

class ArticleUseCaseTest {

@Test
void saveArticle_withValidArticle_savesSuccessfully() {
    IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
    ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
    IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
    ArticleUseCase articleUseCase = new ArticleUseCase(articlePersistencePort, categoryPersistencePort, brandPersistencePort);

    Category category1 = new Category();
    category1.setId(1L);
    Category category2 = new Category();
    category2.setId(2L);

    Brand brand = new Brand();
    brand.setId(1L);

    Article article = new Article();
    article.setCategories(List.of(category1, category2));
    article.setBrand(brand);

    when(brandPersistencePort.exitsById(1L)).thenReturn(true);
    when(categoryPersistencePort.existsById(1L)).thenReturn(true);
    when(categoryPersistencePort.existsById(2L)).thenReturn(true);

    articleUseCase.saveArticle(article);

    verify(articlePersistencePort).saveArticle(article);
}

@Test
void saveArticle_withDuplicateCategories_throwsDuplicateCategoriesException() {
    IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
    ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
    IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
    ArticleUseCase articleUseCase = new ArticleUseCase(articlePersistencePort, categoryPersistencePort, brandPersistencePort);

    Category category1 = new Category();
    category1.setId(1L);
    Category category2 = new Category();
    category2.setId(1L); // Duplicate category ID

    Article article = new Article();
    article.setCategories(List.of(category1, category2));
    article.setBrand(new Brand());

    when(brandPersistencePort.exitsById(anyLong())).thenReturn(true);
    when(categoryPersistencePort.existsById(anyLong())).thenReturn(true);

    assertThrows(DuplicateCategoriesException.class, () -> articleUseCase.saveArticle(article));
}

@Test
void saveArticle_withMoreThanThreeCategories_throwsCategoriesSizeException() {
    IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
    ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
    IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
    ArticleUseCase articleUseCase = new ArticleUseCase(articlePersistencePort, categoryPersistencePort, brandPersistencePort);

    Category category1 = new Category();
    category1.setId(1L);
    Category category2 = new Category();
    category2.setId(2L);
    Category category3 = new Category();
    category3.setId(3L);
    Category category4 = new Category();
    category4.setId(4L);

    Article article = new Article();
    article.setCategories(List.of(category1, category2, category3, category4));
    article.setBrand(new Brand());

    assertThrows(CategoriesSizeException.class, () -> articleUseCase.saveArticle(article));
}


  @Test
void saveArticle_withNonExistentBrand_throwsBrandNotFoundException() {
    IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
    ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
    IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
    ArticleUseCase articleUseCase = new ArticleUseCase(articlePersistencePort, categoryPersistencePort, brandPersistencePort);

    Category category1 = new Category();
    category1.setId(1L);
    Category category2 = new Category();
    category2.setId(2L);

    Brand brand = new Brand();
    brand.setId(1L);

    Article article = new Article();
    article.setCategories(List.of(category1, category2)); // Ensure categories are not null
    article.setBrand(brand);

    when(brandPersistencePort.exitsById(1L)).thenReturn(false);

    assertThrows(BrandNotFoundException.class, () -> articleUseCase.saveArticle(article));
}



    @Test
void getAllArticles_withValidParameters_returnsArticles() {
    IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
    ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
    IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
    ArticleUseCase articleUseCase = new ArticleUseCase(articlePersistencePort, categoryPersistencePort, brandPersistencePort);

    CustomPage<Article> expectedPage = new CustomPage<>();
    when(articlePersistencePort.getAllArticles(0, 10, "ASC", "article")).thenReturn(expectedPage);

    CustomPage<Article> result = articleUseCase.getAllArticles(0, 10, "ASC", "article");

    assertEquals(expectedPage, result);
}
    @Test
    void saveArticle_withNullCategories_throwsCategoriesNullException() {
        IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
        ArticleUseCase articleUseCase = new ArticleUseCase(articlePersistencePort, categoryPersistencePort, brandPersistencePort);

        Article article = new Article();
        article.setCategories(null); // Set categories to null
        article.setBrand(new Brand());

        when(brandPersistencePort.exitsById(anyLong())).thenReturn(true);
        when(categoryPersistencePort.existsById(anyLong())).thenReturn(true);

        assertThrows(CategoriesNullException.class, () -> articleUseCase.saveArticle(article));
    }
}