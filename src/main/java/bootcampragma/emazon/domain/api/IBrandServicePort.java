package bootcampragma.emazon.domain.api;

import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.util.CustomPage;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    CustomPage<Brand> getAllBrand(Integer page, Integer size, String sortDirection);
}
