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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

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
        // Crear PageRequest con los parámetros de paginación y ordenación
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy);

        // Obtener la página de artículos desde el repositorio
        Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageRequest);

        // Mapear las entidades a objetos de dominio Article
        List<Article> articles = articleEntityPage.getContent().stream()
                .map(articleEntityMapper::toArticle)
                .toList();

        // Crear y devolver el CustomArticlePage
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
