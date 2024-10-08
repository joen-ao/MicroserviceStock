package BootcampPragma.emazon.infrastructure.output.jpa.mapper;

import BootcampPragma.emazon.domain.entity.Category;
import BootcampPragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper  {

    CategoryEntity toCategoryEntity(Category category);
    Category toDomainCategory(CategoryEntity category);

}
