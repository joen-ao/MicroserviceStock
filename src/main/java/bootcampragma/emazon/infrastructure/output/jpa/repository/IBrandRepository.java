package bootcampragma.emazon.infrastructure.output.jpa.repository;

import bootcampragma.emazon.infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    Optional<BrandEntity> existsByName(String name);
}
