package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.exception.brand.BrandNotFoundException;
import bootcampragma.emazon.domain.exception.category.CategoryNotFoundException;
import bootcampragma.emazon.domain.spi.IArticlePersistencePort;
import bootcampragma.emazon.domain.util.CustomArticlePage;
import bootcampragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import bootcampragma.emazon.infrastructure.output.jpa.entity.BrandEntity;
import bootcampragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IArticleRepository;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IBrandRepository;
import bootcampragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;
    private final IBrandRepository brandRepository;
    private final ICategoryRepository categoryRepository;


    @Override
    public void saveArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toArticleEntity(article);
        Optional<BrandEntity> optionalBrandEntity= brandRepository.findById(article.getBrand().getId());

        List<CategoryEntity> categoryEntities = article.getCategories().stream()
                .map(category -> categoryRepository.findById(category.getId())
                        .orElseThrow(CategoryNotFoundException::new))
                .toList();

        if(!optionalBrandEntity.isPresent()) {
            throw new BrandNotFoundException();
        }



        articleEntity.setBrandEntity(optionalBrandEntity.get());
        articleEntity.setCategoriesEntity(categoryEntities);
        articleRepository.save(articleEntityMapper.toArticleEntity(article));
    }

    @Override
    public boolean exisyById(Long id) {
        return false;
    }

    @Override
    public CustomArticlePage<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        if(sortBy.equals("categories")) {
            sortBy = "categoriesEntity.name";
        }
        if(sortBy.equals("brand")) {
            sortBy = "brandEntity.name";
        }
        if(sortBy.equals("article")) {
            sortBy = "name";
        }
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy);
        Page<ArticleEntity> articleEntities = articleRepository.findAll(pageable);
        return articleEntityMapper.toCustomPageArticle(articleEntities);
    }

}
