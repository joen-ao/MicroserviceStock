package bootcampragma.emazon.aplication.mapper;

import bootcampragma.emazon.aplication.dto.BrandRequest;
import bootcampragma.emazon.domain.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BrandRequestMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Brand toRequest(BrandRequest brandRequest);

}
