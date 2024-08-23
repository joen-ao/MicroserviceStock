package BootcampPragma.emazon.aplication.mapper;

import BootcampPragma.emazon.aplication.dto.CategoryResponse;
import BootcampPragma.emazon.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

    CategoryResponse toResponse(Category categoryEntity);
}