package BootcampPragma.emazon.infrastructure.output.repository;

import BootcampPragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    // Método para verificar si existe una categoría por nombre
    boolean existsByName(String name);
}
