package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.api.IArticleServicePort;
import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.exception.article.CategoriesNullException;
import bootcampragma.emazon.domain.exception.article.CategoriesSizeException;
import bootcampragma.emazon.domain.exception.article.DuplicateCategoriesException;
import bootcampragma.emazon.domain.exception.brand.BrandNotFoundException;
import bootcampragma.emazon.domain.spi.IArticlePersistencePort;
import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;
import bootcampragma.emazon.domain.util.ArticlesValidation;
import bootcampragma.emazon.domain.util.Constants;
import bootcampragma.emazon.domain.util.CustomPage;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        if (categories == null || categories.isEmpty()) {
            throw new CategoriesNullException();
        }

        Set<Long> categoryIds = new HashSet<>();
        for (Category category : categories) {
            if (!categoryIds.add(category.getId())) {
                throw new DuplicateCategoriesException();
            }
        }

        if (categories.size() > Constants.THREE) {
            throw new CategoriesSizeException();
        }

        if (!brandPersistencePort.exitsById(article.getBrand().getId())) {
            throw new BrandNotFoundException();
        }


        articlePersistencePort.saveArticle(article);
    }

    @Override
    public CustomPage<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        ArticlesValidation.validationGetAllArticles(page, size, sortDirection, sortBy);


        return articlePersistencePort.getAllArticles(page, size, sortDirection, sortBy);
    }
}
