package bootcampragma.emazon.aplication.mapper;

import bootcampragma.emazon.aplication.dto.BrandResponse;
import bootcampragma.emazon.domain.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandResponseMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    BrandResponse toResponse(Brand brandEntity);
}
