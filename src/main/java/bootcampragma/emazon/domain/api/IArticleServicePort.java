package bootcampragma.emazon.domain.api;

import bootcampragma.emazon.domain.entity.Article;

public interface IArticleServicePort {
    void saveArticle(Article article);
}
