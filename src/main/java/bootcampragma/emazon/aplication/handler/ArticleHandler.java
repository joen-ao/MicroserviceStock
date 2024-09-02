package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.request.ArticleRequest;
import bootcampragma.emazon.aplication.handler.interfaces.IArticleHandler;
import bootcampragma.emazon.aplication.mapper.request.ArticleRequestMapper;
import bootcampragma.emazon.domain.api.IArticleServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final ArticleRequestMapper articleRequestMapper;

    @Override
    public void saveArticle(ArticleRequest articleRequest) {
        if (articleRequest == null) {
            throw new IllegalArgumentException("Article request cannot be null");
        }
        articleServicePort.saveArticle(articleRequestMapper.toRequest(articleRequest));
    }
}