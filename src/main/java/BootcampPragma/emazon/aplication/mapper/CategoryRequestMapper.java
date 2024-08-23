package BootcampPragma.emazon.aplication.mapper;

import BootcampPragma.emazon.aplication.dto.CategoryRequest;
import BootcampPragma.emazon.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {

    Category toRequest(CategoryRequest categoryRequest);

}