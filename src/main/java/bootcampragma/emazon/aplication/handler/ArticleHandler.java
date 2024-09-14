package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.request.ArticleRequest;
import bootcampragma.emazon.aplication.dto.response.ArticleResponse;
import bootcampragma.emazon.aplication.handler.interfaces.IArticleHandler;
import bootcampragma.emazon.aplication.mapper.request.ArticleRequestMapper;
import bootcampragma.emazon.aplication.mapper.response.ArticleResponseMapper;
import bootcampragma.emazon.domain.api.IArticleServicePort;
import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.util.CustomArticlePage;
import bootcampragma.emazon.domain.util.ArticlesValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final ArticleRequestMapper articleRequestMapper;
    private final ArticleResponseMapper articleResponseMapper;

    @Override
    public void saveArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toRequest(articleRequest);

        if (articleRequest == null) {
            throw new IllegalArgumentException("Article request cannot be null");
        }
        Brand brand = new Brand();
        brand.setId(articleRequest.getBrandId());

        List<Long> categoryIds = articleRequest.getCategoriesId();
        List<Category> categories = categoryIds.stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                }).toList();

        article.setCategories(categories);
        article.setBrand(brand);

        articleServicePort.saveArticle(article);
    }

    @Override
    public CustomArticlePage<ArticleResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {

        ArticlesValidation.validationGetAllArticles(page, size, sortDirection, sortBy);

        CustomArticlePage<Article> articlePage = articleServicePort.getAllArticles(page, size, sortDirection, sortBy);

        return articleResponseMapper.toResponseList(articlePage);
    }
}