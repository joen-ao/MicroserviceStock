package bootcampragma.emazon.aplication.mapper.request;

import bootcampragma.emazon.aplication.dto.request.BrandRequest;
import bootcampragma.emazon.domain.entity.Brand;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BrandRequestMapper {
    Brand toRequest(BrandRequest brandRequest);

}
