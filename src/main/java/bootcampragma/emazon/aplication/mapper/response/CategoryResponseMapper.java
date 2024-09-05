package bootcampragma.emazon.aplication.mapper.response;

import bootcampragma.emazon.aplication.dto.response.CategoryResponse;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {
    @Mapping(target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")

    CategoryResponse toResponse(Category category);

    default Category toDomain(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        category.setDescription(categoryEntity.getDescription());
        return category;
    }

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