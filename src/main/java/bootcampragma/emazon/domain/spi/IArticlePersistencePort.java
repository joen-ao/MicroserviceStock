package bootcampragma.emazon.domain.spi;

import bootcampragma.emazon.domain.entity.Article;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
}
