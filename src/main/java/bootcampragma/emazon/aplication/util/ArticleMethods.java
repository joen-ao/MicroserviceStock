package bootcampragma.emazon.aplication.util;

import bootcampragma.emazon.aplication.dto.response.ArticleResponse;
import bootcampragma.emazon.aplication.dto.response.BrandResponse;
import bootcampragma.emazon.aplication.dto.response.CategoryResponse;
import bootcampragma.emazon.aplication.mapper.response.BrandResponseMapper;
import bootcampragma.emazon.aplication.mapper.response.CategoryResponseMapper;
import bootcampragma.emazon.domain.api.IBrandServicePort;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import bootcampragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleMethods {

    private ArticleMethods() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }



    public static List<ArticleResponse> mapArticleResponse(List<Article> articles,
                                                           IBrandServicePort brandServicePort,
                                                           ICategoryServicePort categoryServicePort,
                                                           ICategoryRepository categoryRepository,
                                                           BrandResponseMapper brandResponseMapper,
                                                           CategoryResponseMapper categoryResponseMapper) {



        return articles.stream().map(article -> {
            ArticleResponse articleResponse = new ArticleResponse();

            articleResponse.setId(article.getId());
            articleResponse.setName(article.getName());
            articleResponse.setDescription(article.getDescription());
            articleResponse.setPrice(article.getPrice() != null ? article.getPrice().doubleValue() : null);
            articleResponse.setStock(article.getStock() != null ? article.getStock().intValue() : null);

            // Map brand details
            BrandResponse brandResponse = brandResponseMapper.toResponse(brandServicePort.findById(article.getBrandId()));
            articleResponse.setBrand(brandResponse);

            // Map category details using categoryRepository
            List<CategoryResponse> categoryResponses = article.getCategoriesId().stream()
                    .map(categoryId -> {
                        // Fetch category entity from repository by ID
                        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

                        // Ensure categoryEntity fields are not null
                        if (categoryEntity.getId() == null || categoryEntity.getName() == null || categoryEntity.getDescription() == null) {
                            throw new RuntimeException("CategoryEntity fields are null for id: " + categoryId);
                        }

                        // Convert CategoryEntity to Category
                        Category category = categoryResponseMapper.toDomain(categoryEntity);

                        // Convert Category to CategoryResponse
                        CategoryResponse categoryResponse = categoryResponseMapper.toResponse(category);

                        return categoryResponse;
                    }).collect(Collectors.toList());

            articleResponse.setCategories(categoryResponses);
            return articleResponse;
        }).collect(Collectors.toList());
    }
}