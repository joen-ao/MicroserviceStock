package bootcampragma.emazon.aplication.handler.interfaces;

import bootcampragma.emazon.aplication.dto.request.BrandRequest;
import bootcampragma.emazon.aplication.dto.response.BrandResponse;
import bootcampragma.emazon.domain.util.CustomPage;

public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
    CustomPage<BrandResponse> getAllBrand(Integer page, Integer size, String sortDirection);
}
