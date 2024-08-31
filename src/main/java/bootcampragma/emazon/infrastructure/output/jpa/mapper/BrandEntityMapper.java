package bootcampragma.emazon.infrastructure.output.jpa.mapper;

import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {

    BrandEntity toBrandEntity(Brand brand);
    Brand toDomainBrand(BrandEntity brand);

}
