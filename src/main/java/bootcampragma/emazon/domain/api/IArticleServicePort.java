package bootcampragma.emazon.domain.api;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.util.CustomArticlePage;

public interface IArticleServicePort {
    void saveArticle(Article article);
    CustomArticlePage<Article> getAllArticle(Integer page, Integer size, String sortDirection, String sortBy);
}
