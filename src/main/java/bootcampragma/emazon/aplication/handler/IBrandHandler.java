package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.BrandRequest;
import bootcampragma.emazon.aplication.dto.BrandResponse;
import bootcampragma.emazon.domain.util.CustomPageBrand;

public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
    CustomPageBrand<BrandResponse> getAllBrand(Integer page, Integer size, String sortDirection);
}
