package bootcampragma.emazon.aplication.mapper.response;

import bootcampragma.emazon.aplication.dto.response.BrandResponse;
import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.util.CustomPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandResponseMapper {
    @Mapping(target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    BrandResponse toResponse(Brand brandEntity);

    default CustomPage<BrandResponse> toResponseList(CustomPage<Brand> brandRequestPage){
        CustomPage<BrandResponse> pageCustom = new CustomPage<>();

        List<BrandResponse> content = brandRequestPage.getContent().stream()
                .map(this::toResponse)
                .toList();

        pageCustom.setContent(content);
        pageCustom.setTotalPages(brandRequestPage.getTotalPages());
        pageCustom.setTotalElements(brandRequestPage.getTotalElements());
        pageCustom.setPageNumber(brandRequestPage.getPageNumber());
        pageCustom.setPageSize(brandRequestPage.getPageSize());
        return pageCustom;
    }
}
