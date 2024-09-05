package bootcampragma.emazon.aplication.mapper.response;

import bootcampragma.emazon.aplication.dto.response.BrandResponse;
import bootcampragma.emazon.domain.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandResponseMapper {
    @Mapping(target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")

    BrandResponse toResponse(Brand brand);
    List<Brand> toBrandList(List<BrandResponse> brandResponse);
    default List<BrandResponse> toResponseList(List<Brand> brandRequestList){
        return brandRequestList.stream().map(
                brandRequest->{
                    BrandResponse brandResponse = new BrandResponse();
                    brandResponse.setId(brandRequest.getId());
                    brandResponse.setName(brandRequest.getName());
                    brandResponse.setDescription(brandRequest.getDescription());
                    return brandResponse;
                }).toList();
    }
}
