package bootcampragma.emazon.infrastructure.output.jpa.mapper;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper  {

    CategoryEntity toCategoryEntity(Category category);
    Category toDomainCategory(CategoryEntity category);

    List<Category> toCategoryList(List<CategoryEntity> categoryEntities);

}