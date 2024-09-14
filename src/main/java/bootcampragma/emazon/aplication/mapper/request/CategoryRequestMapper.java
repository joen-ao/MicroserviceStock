package bootcampragma.emazon.aplication.mapper.request;

import bootcampragma.emazon.aplication.dto.request.CategoryRequest;
import bootcampragma.emazon.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {

    Category toRequest(CategoryRequest categoryRequest);
}