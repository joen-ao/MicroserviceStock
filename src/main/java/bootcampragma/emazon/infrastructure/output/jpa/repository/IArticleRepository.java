package bootcampragma.emazon.infrastructure.output.jpa.repository;

import bootcampragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
