package bootcampragma.emazon.aplication.handler.interfaces;

import bootcampragma.emazon.aplication.dto.request.ArticleRequest;
import bootcampragma.emazon.aplication.dto.response.ArticleResponse;
import bootcampragma.emazon.domain.util.CustomPage;

public interface IArticleHandler {
    void saveArticle(ArticleRequest articleRequest);
    CustomPage<ArticleResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy);
}

