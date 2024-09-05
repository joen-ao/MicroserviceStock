package bootcampragma.emazon.domain.spi;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.util.CustomArticlePage;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    CustomArticlePage<Article> getAllArticle(Integer page, Integer size, String sortDirection, String sortBy);
}
