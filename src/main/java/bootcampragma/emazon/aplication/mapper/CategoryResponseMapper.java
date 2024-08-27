package bootcampragma.emazon.aplication.mapper;

import bootcampragma.emazon.aplication.dto.CategoryResponse;
import bootcampragma.emazon.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

    CategoryResponse toResponse(Category categoryEntity);
}