package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.spi.IArticlePersistencePort;
import bootcampragma.emazon.domain.util.CustomArticlePage;
import bootcampragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public void saveArticle(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("Article cannot be null");
        }
        articleRepository.save(articleEntityMapper.toArticleEntity(article));
    }

    @Override
    public CustomArticlePage<Article> getAllArticle(Integer page, Integer size, String sortDirection, String sortBy) {

        if (sortBy.equals("brand")) {
            sortBy = "brand.name";
        } else if (sortBy.equals("categories")) {
        sortBy = "categories.name";
        }

        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageable);

        List<Article> articles = articleEntityPage.getContent()
                .stream()
                .map(articleEntityMapper::toArticle)
                .collect(Collectors.toList());

        return new CustomArticlePage<>(
                articles,
                articleEntityPage.getNumber(),
                articleEntityPage.getSize(),
                articleEntityPage.getTotalElements(),
                articleEntityPage.getTotalPages(),
                sortBy
        );
    }


}
