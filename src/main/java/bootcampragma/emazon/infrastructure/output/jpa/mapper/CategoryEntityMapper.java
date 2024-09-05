package bootcampragma.emazon.infrastructure.output.jpa.mapper;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import bootcampragma.emazon.domain.util.CustomPage;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {

    @Named("toCategoryEntity")
    CategoryEntity toCategoryEntity(Category category);


    @Named("toCategoryEntityListToIdList")
    default List<Long> toCategoryEntityListToIdList(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
                .map(CategoryEntity::getId)
                .collect(Collectors.toList());
    }


    Category toDomainCategory(CategoryEntity categoryEntity);

    List<Category> toCategoryList(List<CategoryEntity> categoryEntities);

    default CustomPage<Category> toCustomPage(org.springframework.data.domain.Page<CategoryEntity> categoryEntityPage) {
        List<Category> categoryList = toCategoryList(categoryEntityPage.getContent());
        return new CustomPage<>(
                categoryList,
                categoryEntityPage.getNumber(),
                categoryEntityPage.getSize(),
                categoryEntityPage.getTotalElements(),
                categoryEntityPage.getTotalPages()
        );
    }

    @Named("toCategoryEntity")
    default CategoryEntity toCategoryEntity(Long categoryId) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        return categoryEntity;
    }
}
