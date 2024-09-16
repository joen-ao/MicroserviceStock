package bootcampragma.emazon.infrastructure.output.jpa.mapper;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {

    CategoryEntity toCategoryEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);

    List<Category> toCategoryList(List<CategoryEntity> categoryEntities);

    default CustomPage<Category> toCustomPage(Page<CategoryEntity> page) {
        CustomPage<Category> pageCustom = new CustomPage<>();
        List<Category> content = page.getContent().stream()
                .map(this::toCategory)
                .toList();
        pageCustom.setContent(content);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setPageSize(page.getSize());
        pageCustom.setPageNumber(page.getNumber());
        return pageCustom;
    }
}
