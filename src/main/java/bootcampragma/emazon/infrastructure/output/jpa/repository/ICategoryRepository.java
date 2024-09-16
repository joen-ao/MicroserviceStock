package bootcampragma.emazon.infrastructure.output.jpa.repository;

import bootcampragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByName(String name);
    Optional<CategoryEntity> findByName(String name);

}
