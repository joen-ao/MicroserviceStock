package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.api.IArticleServicePort;
import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.exception.article.CategoriesSizeException;
import bootcampragma.emazon.domain.exception.article.DuplicateCategoriesException;
import bootcampragma.emazon.domain.exception.brand.BrandNotFoundException;
import bootcampragma.emazon.domain.exception.category.CategoryNotFoundException;
import bootcampragma.emazon.domain.spi.IArticlePersistencePort;
import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;
import bootcampragma.emazon.domain.util.ArticlesValidation;
import bootcampragma.emazon.domain.util.CustomArticlePage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort, ICategoryPersistencePort categoryPersistencePort, IBrandPersistencePort brandPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        List<Category> categories = article.getCategories();

        if (categories == null) {
            article.setCategories(new ArrayList<>());
            categories = article.getCategories();
        }

        if (categories.size() != new HashSet<>(categories).size()) {
            throw new DuplicateCategoriesException();
        }

        if (categories.size() > 3) {
            throw new CategoriesSizeException();
        }

        if (!brandPersistencePort.exitsById(article.getBrand().getId())) {
            throw new BrandNotFoundException();
        }

        Set<Long> invalidCategoryIds = categories.stream()
                .map(Category::getId)
                .filter(categoryId -> !categoryPersistencePort.existsById(categoryId))
                .collect(Collectors.toSet());

        if (!invalidCategoryIds.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        articlePersistencePort.saveArticle(article);
    }

    @Override
    public CustomArticlePage<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        ArticlesValidation.validationGetAllArticles(page, size, sortDirection, sortBy);


        return articlePersistencePort.getAllArticles(page, size, sortDirection, sortBy);
    }
}
