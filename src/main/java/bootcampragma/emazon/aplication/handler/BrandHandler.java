package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.request.BrandRequest;
import bootcampragma.emazon.aplication.dto.response.BrandResponse;
import bootcampragma.emazon.aplication.handler.interfaces.IBrandHandler;
import bootcampragma.emazon.aplication.mapper.request.BrandRequestMapper;
import bootcampragma.emazon.aplication.mapper.response.BrandResponseMapper;
import bootcampragma.emazon.aplication.util.AplicationConstants;
import bootcampragma.emazon.domain.api.IBrandServicePort;
import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.util.CustomPage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
            throw new IllegalArgumentException(AplicationConstants.BRAND_CANNOT_BE_NULL);
        }
        brandServicePort.saveBrand(brandRequestMapper.toRequest(brandRequest));
    }

    @Override
    public CustomPage<BrandResponse> getAllBrand(Integer page, Integer size, String sortDirection) {

        CustomPage<Brand> brandPage = brandServicePort.getAllBrand(page, size, sortDirection);
        return brandResponseMapper.toResponseList(brandPage);

    }
}
