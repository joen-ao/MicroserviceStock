package bootcampragma.emazon.aplication.mapper.response;

import bootcampragma.emazon.aplication.dto.response.CategoryResponse;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.util.CustomPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {
    @Mapping(target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")

    CategoryResponse toResponse(Category categoryEntity);

    default CustomPage<CategoryResponse> toResponseList(CustomPage<Category> categoryRequestPage){
        CustomPage<CategoryResponse> pageCustom = new CustomPage<>();

        List<CategoryResponse> content = categoryRequestPage.getContent().stream()
                .map(this::toResponse)
                .toList();

        pageCustom.setContent(content);
        pageCustom.setTotalPages(categoryRequestPage.getTotalPages());
        pageCustom.setTotalElements(categoryRequestPage.getTotalElements());
        pageCustom.setPageNumber(categoryRequestPage.getPageNumber());
        pageCustom.setPageSize(categoryRequestPage.getPageSize());
        return pageCustom;
    }


}