package bootcampragma.emazon.infrastructure.output.jpa.mapper;

import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {

    BrandEntity toBrandEntity(Brand brand);
    Brand toDomainBrand(BrandEntity brand);

    List<Brand> toBrandList(List<BrandEntity> brandEntities);

    default CustomPage<Brand> toCustomPage(org.springframework.data.domain.Page<BrandEntity> brandEntityPage) {
        List<Brand> brandList = toBrandList(brandEntityPage.getContent());
        return new CustomPage<>(
                brandList,
                brandEntityPage.getNumber(),
                brandEntityPage.getSize(),
                brandEntityPage.getTotalElements(),
                brandEntityPage.getTotalPages()
        );
    }

    @Named("toBrandEntity")
    default BrandEntity toBrandEntity(Long brandId) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brandId);
        return brandEntity;
    }

}