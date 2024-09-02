package bootcampragma.emazon.aplication.handler.interfaces;

import bootcampragma.emazon.aplication.dto.request.BrandRequest;
import bootcampragma.emazon.aplication.dto.response.BrandResponse;
import bootcampragma.emazon.domain.util.CustomPageBrand;

public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
    CustomPageBrand<BrandResponse> getAllBrand(Integer page, Integer size, String sortDirection);
}
