package bootcampragma.emazon.domain.api;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.util.CustomPage;

public interface IArticleServicePort {
    void saveArticle(Article article);
    CustomPage<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy);
}
