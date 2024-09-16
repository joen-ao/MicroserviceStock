package bootcampragma.emazon.domain.spi;

import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.util.CustomPage;


public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    CustomPage<Brand> getAllBrand(Integer page, Integer size, String sortDirection);
    boolean findByName(String name);
    boolean exitsById(Long id);
}
