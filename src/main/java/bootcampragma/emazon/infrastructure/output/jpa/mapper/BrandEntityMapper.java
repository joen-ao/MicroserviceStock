package bootcampragma.emazon.infrastructure.output.jpa.mapper;

import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {

    BrandEntity toBrandEntity(Brand brand);
    Brand toDomainBrand(BrandEntity brand);

    List<Brand> toBrandList(List<BrandEntity> brandEntities);

    default CustomPage<Brand> toCustomPage(Page<BrandEntity> page) {
        CustomPage<Brand> pageCustom = new CustomPage<>();

        List<Brand> content = page.getContent().stream()
                .map(this::toDomainBrand)
                .toList();

        pageCustom.setContent(content);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setPageSize(page.getSize());
        pageCustom.setPageNumber(page.getNumber());
        return pageCustom;
    }

}