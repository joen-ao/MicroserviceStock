package bootcampragma.emazon.infrastructure.output.jpa.repository;

import bootcampragma.emazon.infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    boolean existsByName(String name);
}
