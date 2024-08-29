package bootcampragma.emazon.aplication.mapper;

import bootcampragma.emazon.aplication.dto.CategoryRequest;
import bootcampragma.emazon.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")

    Category toRequest(CategoryRequest categoryRequest);
}