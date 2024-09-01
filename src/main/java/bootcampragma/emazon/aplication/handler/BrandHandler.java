package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.BrandRequest;
import bootcampragma.emazon.aplication.dto.BrandResponse;
import bootcampragma.emazon.aplication.mapper.BrandRequestMapper;
import bootcampragma.emazon.aplication.mapper.BrandResponseMapper;
import bootcampragma.emazon.domain.api.IBrandServicePort;
import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.domain.util.CustomPageBrand;
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
    public CustomPageBrand<BrandResponse> getAllBrand(Integer page, Integer size, String sortDirection) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }

        CustomPage<Brand> brandPage = brandServicePort.getAllBrand(page, size, sortDirection);
        List<BrandResponse> brandResponses = brandResponseMapper.toResponseList(brandPage.getContent());  // Usando BrandResponseMapper

        return new CustomPageBrand<>(
                brandResponses,
                brandPage.getPageNumber(),
                brandPage.getPageSize(),
                brandPage.getTotalElements(),
                brandPage.getTotalPages()
        );
    }
}
