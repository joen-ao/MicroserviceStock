package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.spi.IArticlePersistencePort;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

}
