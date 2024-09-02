package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.api.IArticleServicePort;
import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.exception.article.CategoriesSizeException;
import bootcampragma.emazon.domain.exception.article.DuplicateCategoriesException;
import bootcampragma.emazon.domain.spi.IArticlePersistencePort;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort) {

        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        if(article.getCategoriesId().size() != new HashSet<>(article.getCategoriesId()).size()){
            throw new DuplicateCategoriesException();
        }
        if(article.getCategoriesId().isEmpty()|| article.getCategoriesId().size()>3){
            throw new CategoriesSizeException();
        }

        articlePersistencePort.saveArticle(article);
    }
}