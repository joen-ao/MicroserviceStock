package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.request.BrandRequest;
import bootcampragma.emazon.aplication.dto.response.BrandResponse;
import bootcampragma.emazon.aplication.handler.interfaces.IBrandHandler;
import bootcampragma.emazon.aplication.mapper.request.BrandRequestMapper;
import bootcampragma.emazon.aplication.mapper.response.BrandResponseMapper;
import bootcampragma.emazon.domain.api.IBrandServicePort;
import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.exception.category.InvalidSortDirectionException;
import bootcampragma.emazon.domain.util.CustomPage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        if (brandRequest == null) {
            throw new IllegalArgumentException("Brand request cannot be null");
        }
        brandServicePort.saveBrand(brandRequestMapper.toRequest(brandRequest));
    }

    @Override
    public CustomPage<BrandResponse> getAllBrand(Integer page, Integer size, String sortDirection) {
        if (page < 0 ) { //MOVER VALIDACIONES
            throw new IllegalArgumentException("Page number cannot be negative and null");
        }
        if (!sortDirection.equalsIgnoreCase("asc") && !sortDirection.equalsIgnoreCase("desc")) {
            throw new InvalidSortDirectionException("Invalid sort direction");
        }
        if(size == null || size < 0){
            throw new IllegalArgumentException("Size number cannot be negative and null");
        }

        CustomPage<Brand> brandPage = brandServicePort.getAllBrand(page, size, sortDirection);
        List<BrandResponse> brandResponses = brandResponseMapper.toResponseList(brandPage.getContent());

        return new CustomPage<>(
                brandResponses,
                brandPage.getPageNumber(),
                brandPage.getPageSize(),
                brandPage.getTotalElements(),
                brandPage.getTotalPages()
        );
    }
}
