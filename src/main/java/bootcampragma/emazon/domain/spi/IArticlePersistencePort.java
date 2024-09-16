package bootcampragma.emazon.domain.spi;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.util.CustomPage;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    boolean exisyById(Long id);
    CustomPage<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy);
}
