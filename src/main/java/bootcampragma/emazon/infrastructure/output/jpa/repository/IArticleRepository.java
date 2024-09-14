package bootcampragma.emazon.infrastructure.output.jpa.repository;

import bootcampragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> , JpaSpecificationExecutor<ArticleEntity> {

}
