package bootcampragma.emazon.aplication.mapper;

import bootcampragma.emazon.aplication.dto.CategoryResponse;
import bootcampragma.emazon.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")

    CategoryResponse toResponse(Category categoryEntity);

    default List<CategoryResponse> toResponseList(List<Category> categoryRequestList){
        return categoryRequestList.stream().map(
                categoryRequest-> {
                    CategoryResponse categoryResponse = new CategoryResponse();
                    categoryResponse.setId(categoryRequest.getId());
                    categoryResponse.setName(categoryRequest.getName());
                    categoryResponse.setDescription(categoryRequest.getDescription());
                    return categoryResponse;
                }).toList();
    }
}