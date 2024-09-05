package bootcampragma.emazon.aplication.handler.interfaces;

import bootcampragma.emazon.aplication.dto.request.ArticleRequest;
import bootcampragma.emazon.aplication.dto.response.ArticleResponse;
import bootcampragma.emazon.domain.util.CustomArticlePage;

public interface IArticleHandler {
    void saveArticle(ArticleRequest articleRequest);
    CustomArticlePage<ArticleResponse> getAllArticle(Integer page, Integer size, String sortDirection, String sortBy);
}

