package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.request.ArticleRequest;
import bootcampragma.emazon.aplication.dto.response.ArticleResponse;
import bootcampragma.emazon.aplication.handler.interfaces.IArticleHandler;
import bootcampragma.emazon.aplication.mapper.request.ArticleRequestMapper;
import bootcampragma.emazon.aplication.mapper.response.BrandResponseMapper;
import bootcampragma.emazon.aplication.mapper.response.CategoryResponseMapper;
import bootcampragma.emazon.aplication.util.ArticleMethods;
import bootcampragma.emazon.domain.api.IArticleServicePort;
import bootcampragma.emazon.domain.api.IBrandServicePort;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.util.CustomArticlePage;
import bootcampragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import bootcampragma.emazon.aplication.util.ArticlesValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final IBrandServicePort brandServicePort;
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRepository categoryRepository;
    private final BrandResponseMapper brandResponseMapper;
    private final CategoryResponseMapper categoryResponseMapper;
    private final ArticleRequestMapper articleRequestMapper;

    @Override
    public void saveArticle(ArticleRequest articleRequest) {
        if (articleRequest == null) {
            throw new IllegalArgumentException("Article request cannot be null");
        }
        articleServicePort.saveArticle(articleRequestMapper.toRequest(articleRequest));
    }

    @Override
    public CustomArticlePage<ArticleResponse> getAllArticle(Integer page, Integer size, String sortDirection, String sortBy) {

        ArticlesValidation.validationGetAllArticles(page, size, sortDirection, sortBy);

        CustomArticlePage<Article> articlePage = articleServicePort.getAllArticle(page, size, sortDirection, sortBy);

        List<ArticleResponse> articleResponses = ArticleMethods.mapArticleResponse(
                articlePage.getContent(),
                brandServicePort,
                categoryServicePort,
                categoryRepository,
                brandResponseMapper,
                categoryResponseMapper
        );


        return new CustomArticlePage<>(
                articleResponses,
                articlePage.getPageNumber(),
                articlePage.getPageSize(),
                articlePage.getTotalElements(),
                articlePage.getTotalPages(),
                articlePage.getSortBy()
        );
    }
}
